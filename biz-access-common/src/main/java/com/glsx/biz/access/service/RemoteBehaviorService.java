package com.glsx.biz.access.service;

import com.glsx.biz.access.common.entity.RemoteBehavior;
import com.glsx.biz.access.common.vo.NotifyMsg;
import com.glsx.biz.access.common.vo.SyncMedia;
import com.glsx.cloudframework.exception.ServiceException;

import java.io.Serializable;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/14
 * Time: 12:04
 *
 * Description: 远程拍照服务接口
 */
public interface RemoteBehaviorService {

    /**
     * 发送用户请求
     * @param remoteBehavior    用户请求参数
     * @return
     * @throws ServiceException
     */
    public boolean sendOperator(RemoteBehavior remoteBehavior) throws ServiceException;

    /**
     * 获取终端设备使用状态
     * @param userId    嘀嘀号
     * @return
     * @throws ServiceException
     */
    public boolean getDeviceStatus(Integer userId) throws ServiceException;

    /**
     * 唤醒设备
     * @param userId    嘀嘀号
     * @return
     * @throws ServiceException
     */
    public boolean awakeDevice(Integer userId) throws ServiceException;

    /**
     *  通过嘀嘀号和媒体操作id获取操作记录
     * @param userId        嘀嘀号
     * @param mediaId       媒体操作流水号
     * @return
     * @throws ServiceException
     */
    public RemoteBehavior getRemoteBehaviorByUserId(Integer userId, String mediaId) throws ServiceException;

    /**
     * 通过mediaId获取操作记录
     * @param mediaId
     * @return
     * @throws ServiceException
     */
    public RemoteBehavior getRemoteBehaviorByMediaId(String mediaId) throws ServiceException;

    /**
     * 获取最新远程操作记录
     * @param userId
     * @return
     */
    public RemoteBehavior getLastRemoteBehavior(Integer userId) throws ServiceException;

    /**
     * 异步处理接收到的消息
     * @param syncMedia
     * @throws ServiceException
     */
    public void sendSyncMediaMsg(SyncMedia syncMedia) ;

    /**
     * 通过imei获取最后一次碰撞操作
     * @param imei
     * @return
     * @throws ServiceException
     */
    public RemoteBehavior getRemoteBehaviorLastByIMEI(String imei) ;

    /**
     * 处理同步过来的数据
     * @param syncMedia
     */
    public void saveSyncMeida(SyncMedia syncMedia);

    /**
     * 获取用户最新的拍照信息
     * @param userId 嘀嘀号
     * @return
     * @throws ServiceException
     */
    public RemoteBehavior getRemoteBehaviorConsummate(Integer userId) throws ServiceException;
}
