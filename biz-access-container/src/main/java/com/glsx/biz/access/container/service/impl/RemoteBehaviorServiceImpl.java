package com.glsx.biz.access.container.service.impl;

import com.glsx.biz.access.common.entity.Media;
import com.glsx.biz.access.common.entity.RemoteBehavior;
import com.glsx.biz.access.common.entity.enums.*;
import com.glsx.biz.access.common.vo.NotifyMsg;
import com.glsx.biz.access.common.vo.SyncMedia;
import com.glsx.biz.access.common.vo.SyncMediaCache;
import com.glsx.biz.access.container.contants.ServiceConstants;
import com.glsx.biz.access.container.contants.ServiceExceptionType;
import com.glsx.biz.access.container.dao.access.RemoteBehaviorDao;
import com.glsx.biz.access.container.utils.ApacheHttpUtils;
import com.glsx.biz.access.container.utils.DateUtils;
import com.glsx.biz.access.container.utils.HMACSHA1;
import com.glsx.biz.access.container.utils.JsonUtil;
import com.glsx.biz.access.service.MediaService;
import com.glsx.biz.access.service.RemoteBehaviorService;
import com.glsx.biz.user.common.entity.PhysicalDevice;
import com.glsx.biz.user.common.entity.User;
import com.glsx.biz.user.service.PhysicalDeviceService;
import com.glsx.biz.user.service.UserService;
import com.glsx.cloudframework.core.context.config.PropertyConfigurer;
import com.glsx.cloudframework.core.util.Base64;
import com.glsx.cloudframework.core.util.DateFormatUtil;
import com.glsx.cloudframework.core.util.SpringUtil;
import com.glsx.cloudframework.core.util.StringUtils;
import com.glsx.cloudframework.exception.ServiceException;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/15
 * Time: 11:42
 * <p>
 * Description 远程操作服务实现:
 */
@Service("remoteBehaviorService")
public class RemoteBehaviorServiceImpl implements RemoteBehaviorService {

    private static final Logger LOGGER = Logger.getLogger(RemoteBehaviorServiceImpl.class);

    @Resource
    private RemoteBehaviorDao remoteBehaviorDao;

    @Resource
    private UserService userService;

    @Resource
    private PhysicalDeviceService physicalDeviceService;

    @Resource
    private MediaService mediaService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean sendOperator(RemoteBehavior remoteBehavior) throws ServiceException {
        if (remoteBehavior == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        /*RemoteBehavior behaviorBase = remoteBehaviorDao.getRemoteBehaviorByMediaId(remoteBehavior.getMediaId());
        if (behaviorBase != null) {
            throw new ServiceException(ServiceExceptionType.CODE_REMOTEBEHAVIOR_EXIST, ServiceExceptionType.MSG_REMOTEBEHAVIOR_EXIST);
        }*/

        User user = userService.getByUserId(remoteBehavior.getUserId());
        if (user == null) {
            throw new ServiceException(ServiceExceptionType.CODE_USER_NOT_EXIST, ServiceExceptionType.MSG_USER_NOT_EXIST);
        }

        PhysicalDevice phyDevice = null;
        if (user.getPhysicalId() != null) {
            phyDevice = physicalDeviceService.getDevice(user.getPhysicalId());
            if (!StringUtils.isEmpty(phyDevice.getReportSn())) {
                remoteBehavior.setImei(phyDevice.getReportSn());
            } else {
                throw new ServiceException(ServiceExceptionType.CODE_USERID_NOT_BIND_DEVICE, ServiceExceptionType.MSG_USERID_NOT_BIND_DEVICE);
            }
        }

        String opType = "takeVideo";
        int cameroNum = 1;
        int videoTimes = 1;
        if (remoteBehavior.getOperateType().getId() != null && remoteBehavior.getOperateType().getId().equals(Operate.OPERATE_PHOTO.getId())) {
            opType = "takePhoto";
        }
        if (remoteBehavior.getVideoTime() != null) {
            if (remoteBehavior.getVideoTime().intValue() == 10) {
                videoTimes = 2;
            } else if (remoteBehavior.getVideoTime().intValue() == 15) {
                videoTimes = 3;
            }
        }
        if (remoteBehavior.getOperateValue().getId() != null) {
            cameroNum = remoteBehavior.getOperateValue().getId();
        }

        long mediaId = userService.getMessageSeqId("media_id");
        remoteBehavior.setMediaId(String.valueOf(mediaId));

        long timesTamp = System.currentTimeMillis();
        JSONObject paramJson = new JSONObject();
        paramJson.element("appID", ServiceConstants.SEND_COMMON_APPID);
        paramJson.element("appSecret", ServiceConstants.SEND_COMMON_APPSECRET);
        paramJson.element("curTime", timesTamp);
        paramJson.element("imei", remoteBehavior.getImei());
        paramJson.element("opType", opType);
        paramJson.element("camerano", cameroNum);//1.前置摄像头  2.后置摄像头 3.前+后摄像头
		paramJson.element("recordtime", videoTimes);//取值 1:5秒 2:10秒 3:15秒 4:20秒(不支持20秒)
        paramJson.element("mediaid", mediaId);
        // 注意：加密时参数字段需要按照首字母进行排序
        String sign = Base64.encode(HMACSHA1.getInstance().hmacshaEncrypt("appID="+ServiceConstants.SEND_COMMON_APPID + "&camerano=" + cameroNum + "&curTime=" + timesTamp + "&imei=" + remoteBehavior.getImei() + "&opType=" + opType + "&recordtime=" + videoTimes, ServiceConstants.SEND_COMMON_APPSECRET));
        paramJson.element("sign", sign);

        String result = ApacheHttpUtils.URLPostJson(ServiceConstants.SEND_COMMOND_URL, paramJson, "UTF-8");
        remoteBehavior.setReqResult(result);
        JSONObject resultJson = JSONObject.fromObject(result);
        int code = resultJson.getInt("status_code");
        if (code == 0) {
            remoteBehavior.setStatus(RHCStatus.RHC_STATUS_SENDED);
        } else {
            remoteBehavior.setStatus(RHCStatus.REQ_SOURCE_SEND_FAIL);
        }

        remoteBehavior.setReciverStatus(ReciverStatus.MEDIA_UN_RECIVER);
        boolean response = false;
        Integer rId = remoteBehaviorDao.save(remoteBehavior);
        if (rId != null && rId.intValue() > 0) {
            response = true;
        }
        return response;
    }

    @Override
    public boolean getDeviceStatus(Integer userId) throws ServiceException {
        if (userId == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        User user = userService.getByUserId(userId);
        if (user == null) {
            throw new ServiceException(ServiceExceptionType.CODE_USER_NOT_EXIST, ServiceExceptionType.MSG_USER_NOT_EXIST);
        }

        String imei = "";
        PhysicalDevice phyDevice = null;
        if (user.getPhysicalId() != null) {
            phyDevice = physicalDeviceService.getDevice(user.getPhysicalId());
            if (!StringUtils.isEmpty(phyDevice.getReportSn())) {
                imei = phyDevice.getReportSn();
            } else {
                throw new ServiceException(ServiceExceptionType.CODE_USERID_NOT_BIND_DEVICE, ServiceExceptionType.MSG_USERID_NOT_BIND_DEVICE);
            }
        }

        long timesTamp = System.currentTimeMillis();
        JSONObject paramJson = new JSONObject();
        paramJson.element("appID", ServiceConstants.SEND_COMMON_APPID);
        paramJson.element("appSecret", ServiceConstants.SEND_COMMON_APPSECRET);
        paramJson.element("curTime", timesTamp);
        paramJson.element("imei", imei);
        String sign = Base64.encode(HMACSHA1.getInstance().hmacshaEncrypt("appID="+ServiceConstants.SEND_COMMON_APPID + "&curTime=" + timesTamp + "&imei=" + imei, ServiceConstants.SEND_COMMON_APPSECRET));
        paramJson.element("sign", sign);

        String result = ApacheHttpUtils.URLPostJson(ServiceConstants.SEND_DEVICESTATUS_URL, paramJson, "UTF-8");
        JSONObject resJson = JSONObject.fromObject(result);
        int statusCode = resJson.getInt("status_code");
        boolean isOnline = resJson.getBoolean("is_online");
        if (statusCode == 0 && isOnline) {
            return true;
        }
        return false;
    }

    @Override
    public boolean awakeDevice(Integer userId) throws ServiceException {

        return false;
    }

    @Override
    public RemoteBehavior getRemoteBehaviorByUserId(Integer userId, String mediaId) throws ServiceException {
        if (userId == null || mediaId == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        return remoteBehaviorDao.getRemoteBehaviorByUserId(userId, mediaId);
    }

    @Override
    public RemoteBehavior getRemoteBehaviorByMediaId(String mediaId) throws ServiceException {
        if (StringUtils.isEmpty(mediaId)) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        return remoteBehaviorDao.getRemoteBehaviorByMediaId(mediaId);
    }

    @Override
    public RemoteBehavior getLastRemoteBehavior(Integer userId) throws ServiceException {
        if (userId == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        return remoteBehaviorDao.getLastRemoteBehavior(userId);
    }

    /** 变为内部使用方法，不对外开放接口 */
    public RemoteBehavior saveRemoteBehavior(RemoteBehavior remoteBehavior) throws ServiceException {
        if (remoteBehavior == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        long mediaId = userService.getMessageSeqId("media_id");
        remoteBehavior.setMediaId(String.valueOf(mediaId));

        Integer rId = remoteBehaviorDao.save(remoteBehavior);
        if (rId != null) {
            remoteBehavior.setId(rId);
        } else {
            throw new ServiceException(ServiceExceptionType.CODE_SAVE_REMOTEBEHAVIOR_ERROR, ServiceExceptionType.MSG_SAVE_REMOTEBEHAVIOR_ERROR);
        }

        return remoteBehavior;
    }

    @Override
    public void sendSyncMediaMsg(SyncMedia syncMedia) {
        String syncTopic = PropertyConfigurer.getString("sync.media.topic");
        Producer<String, String> producer = (Producer) SpringUtil.getBean("producer");
        KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>(syncTopic, JSONObject.fromObject(syncMedia).toString());
        producer.send(keyedMessage);
        LOGGER.warn("notify_syncMedia_msg ：" + syncTopic + "====>" + syncMedia.toString());
    }

    @Override
    public RemoteBehavior getRemoteBehaviorLastByIMEI(String imei) {
        return remoteBehaviorDao.getRemoteBehaviorLastByIMEI(imei);
    }

    @Override
    public void saveSyncMeida(SyncMedia syncMedia) {

        Integer userId = syncMedia.getUserid();
        Integer source = syncMedia.getAppfromtype();
        String imei = syncMedia.getImei();
        String mediaId = syncMedia.getMediaid();
        String data = syncMedia.getData();
        String gpsLong = syncMedia.getLongi();
        String gpsLat = syncMedia.getLate();
        Integer reportReason = syncMedia.getReportreason();
        String mediaDateValue = syncMedia.getCreatetime();

        // 是否推送消息
        boolean isNotify = false;
        // 是否更新已接收状态
        boolean isReciverStatus = false;
        RemoteBehavior remoteBehavior = null;
        List<Media> parseMedias = JsonUtil.parseMediaByDocument(data);
        if (reportReason == 3) {
            source = 3;// 3 为增加的类型，表示碰撞上报的数据记录
            SyncMediaCache syncMediaCache = this.getSyncMediaCache(imei);
            if (syncMediaCache == null || (syncMediaCache.getPreState().intValue() == syncMediaCache.getBackState().intValue())) {
                syncMediaCache = initSyncMediaCache(imei, parseMedias, mediaDateValue);
                remoteBehavior = createAttackMedia(imei, parseMedias, source);
                if (remoteBehavior == null) {
                    return;
                }
            } else {
                int betweenDate = DateUtils.lastingForDate(DateFormatUtil.toDate(syncMediaCache.getLastDate(), DateFormatUtil.pattern19), DateFormatUtil.toDate(syncMedia.getCreatetime(), DateFormatUtil.pattern19));
                if (betweenDate > 60) {
                    syncMediaCache = initSyncMediaCache(imei, parseMedias, mediaDateValue);
                    remoteBehavior = createAttackMedia(imei, parseMedias, source);
                    if (remoteBehavior == null) {
                        return;
                    }
                } else  if (syncMediaCache.getPreState().intValue() == 2 && syncMediaCache.getBackState().intValue() == 1) {
                    if (parseMedias.get(0).getMirroType() == 1) {
                        syncMediaCache = initSyncMediaCache(imei, parseMedias, mediaDateValue);
                        remoteBehavior = createAttackMedia(imei, parseMedias, source);
                        if (remoteBehavior == null) {
                            return;
                        }
                    } else {
                        syncMediaCache.setBackState(2);
                        putSyncMediaCache(imei, syncMediaCache);
                        isNotify = true;
                        isReciverStatus = true;
                    }
                    remoteBehavior = this.getRemoteBehaviorLastByIMEI(imei);
                } else if (syncMediaCache.getPreState().intValue() == 1 && syncMediaCache.getBackState().intValue() == 2) {
                    if (parseMedias.get(0).getMirroType() == 1) {
                        syncMediaCache.setPreState(2);
                        putSyncMediaCache(imei, syncMediaCache);
                        isNotify = true;
                        isReciverStatus = true;
                    } else {
                        syncMediaCache = initSyncMediaCache(imei, parseMedias, mediaDateValue);
                        remoteBehavior = createAttackMedia(imei, parseMedias, source);
                        if (remoteBehavior == null) {
                            return;
                        }
                    }
                    remoteBehavior = this.getRemoteBehaviorLastByIMEI(imei);
                }
            }
        } else {
            isNotify = true;
            isReciverStatus = true;
        }

        if (remoteBehavior == null) {
            try {
                remoteBehavior = this.getRemoteBehaviorByMediaId(mediaId);
            } catch (com.glsx.cloudframework.exception.ServiceException e) {
                LOGGER.warn("===SYNC_MEDIA===get_RemoteBehavior_Excep=== [mediaId=" + mediaId + "]");
            }
        }

        userId = (remoteBehavior != null) ? remoteBehavior.getUserId() : userId;
        Integer behaviorId = (remoteBehavior != null) ? remoteBehavior.getId() : null;
        Date mediaDate = (StringUtils.isEmpty(mediaDateValue)) ? new Date() : DateFormatUtil.toDate(mediaDateValue, DateFormatUtil.pattern19);

        for (Media media : parseMedias) {
            try {
                media.setUserId(userId);
                media.setGpsLong(gpsLong);
                media.setGpsLat(gpsLat);
                media.setReportReason(reportReason);
                media.setBehaviorId(behaviorId);
                media.setMediaDate(mediaDate);

                mediaService.saveMedia(media);
            } catch (com.glsx.cloudframework.core.exception.ServiceException e) {
                LOGGER.warn("===SYNC_MEDIA===saveException==" + e.getCode() + "_" + e.getMessage());
            }
        }

        if (isReciverStatus) {
            remoteBehaviorDao.updateReciverStatus(remoteBehavior.getId(), ReciverStatus.MEDIA_RECIVERED);
        }

        if (isNotify) {
            NotifyMsg notifyMsg = new NotifyMsg();
            notifyMsg.setUserId(userId);
            notifyMsg.setMediaType(parseMedias.get(0).getMediaType());
            notifyMsg.setMediaId(remoteBehavior.getMediaId());
            notifyMsg.setBehaviorId(remoteBehavior.getId());
            notifyMsg.setSourceId(remoteBehavior.getSource().getId());
            notifyMsg.setReportReason(reportReason);
            this.sendNotifyMsg(notifyMsg);

            this.syncClientLocation(userId, gpsLong, gpsLat, mediaDateValue);
        }
    }

    @Override
    public RemoteBehavior getRemoteBehaviorConsummate(Integer userId) throws ServiceException {
        if (userId == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        return remoteBehaviorDao.getRemoteBehaviorConsummate(userId, ReciverStatus.MEDIA_RECIVERED);
    }

    // saveSyncMeida -Tool 初始化同步拍照信息缓存
    private SyncMediaCache initSyncMediaCache(String imei, List<Media> parseMedias, String mediaDateValue) {
        SyncMediaCache syncMediaCache = new SyncMediaCache();
        syncMediaCache.setImei(imei);
        if (parseMedias.get(0).getMirroType() == 1) {
            syncMediaCache.setPreState(2);
        } else {
            syncMediaCache.setBackState(2);
        }
        syncMediaCache.setLastDate(mediaDateValue);

        putSyncMediaCache(imei, syncMediaCache);
        return syncMediaCache;
    }

    // saveSyncMeida -Tool 创建操作记录生成mediaId
    private RemoteBehavior createAttackMedia(String imei, List<Media> parseMedias, Integer source) {
        RemoteBehavior remoteBehavior = new RemoteBehavior();
        try {
            Integer userId = null;
            PhysicalDevice phyDevice = physicalDeviceService.getPhysicalByReportSn(imei);
            if (phyDevice != null) {
                User user = userService.getByPhysicalId(phyDevice.getId());
                userId = (user != null) ? user.getUserId() : null;
            }
            if (userId == null) {
                LOGGER.warn("===SYNC_MEDIA===[code=5101,msg=用户未正常使用设备]==" + imei);
                return null;
            }

            remoteBehavior = new RemoteBehavior();
            remoteBehavior.setUserId(userId);
            remoteBehavior.setOperateType(Operate.OPERATE_PHOTO);
            remoteBehavior.setOperateValue(MirroPosition.MIRRO_POSITION_DOUBLE);
            remoteBehavior.setImei(imei);
            remoteBehavior.setStatus(RHCStatus.RHC_STATUS_SENDED);
            remoteBehavior.setSource(ReqSource.REQ_SOURCE_ATTACK);
            remoteBehavior.setReciverStatus(ReciverStatus.MEDIA_UN_RECIVER);
            remoteBehavior = this.saveRemoteBehavior(remoteBehavior);
        } catch (com.glsx.cloudframework.exception.ServiceException e) {
            LOGGER.warn("===SYNC_MEDIA===[code="+e.getCode()+",msg="+e.getMessage()+"]==" + imei);
            return null;
        }
        return remoteBehavior;
    }

    private void sendNotifyMsg(NotifyMsg notifyMsg) {
        String notifyTopic = PropertyConfigurer.getString("assign.topic");
        Producer<String, String> producer = (Producer) SpringUtil.getBean("producer");
        KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>(notifyTopic, JSONObject.fromObject(notifyMsg).toString());
        producer.send(keyedMessage);
        LOGGER.warn("notify_media_msg ：" + notifyTopic + "====>" + notifyMsg.toString());
    }

    private void syncClientLocation(Integer userId, String gpsLong, String gpsLat, String mediaDateValue) {
        if (!StringUtils.isEmpty(gpsLong) && !StringUtils.isEmpty(gpsLat)) {
            String syncTopic = PropertyConfigurer.getString("sync.client.location.topic");
            JSONObject reqJson = new JSONObject();
            reqJson.put("userID", String.valueOf(userId));
            reqJson.put("gpsTime", mediaDateValue);
            reqJson.put("lng", gpsLong);
            reqJson.put("lat", gpsLat);

            Producer<String, String> producer = (Producer) SpringUtil.getBean("producer");
            KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>(syncTopic, reqJson.toString());
            producer.send(keyedMessage);
            LOGGER.warn("sync_client_location ：" + syncTopic + "====>" + reqJson.toString());
        }
    }

    // 获取缓存
    private SyncMediaCache getSyncMediaCache(String imei) {
        return (SyncMediaCache) redisTemplate.boundHashOps(ServiceConstants.SYNC_MEDIA_OBJECT).get(imei);
    }

    // 插入，更新缓存
    private void putSyncMediaCache(String imei, SyncMediaCache syncMediaCache) {
        redisTemplate.boundHashOps(ServiceConstants.SYNC_MEDIA_OBJECT).put(imei, syncMediaCache);
    }

}