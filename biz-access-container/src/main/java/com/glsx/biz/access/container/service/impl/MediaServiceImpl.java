package com.glsx.biz.access.container.service.impl;

import com.glsx.biz.access.common.entity.Media;
import com.glsx.biz.access.common.entity.RemoteBehavior;
import com.glsx.biz.access.common.vo.MediaSearch;
import com.glsx.biz.access.container.contants.ServiceExceptionType;
import com.glsx.biz.access.container.dao.access.MediaDao;
import com.glsx.biz.access.container.utils.ApacheHttpUtils;
import com.glsx.biz.access.service.MediaService;
import com.glsx.biz.access.service.RemoteBehaviorService;
import com.glsx.cloudframework.core.datastructure.page.Pagination;
import com.glsx.cloudframework.core.exception.ServiceException;
import com.glsx.cloudframework.core.util.StringUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/15
 * Time: 10:43
 * <p>
 * Description 多媒体服务实现:
 */
@Service("mediaService")
public class MediaServiceImpl implements MediaService {

    private static final Logger LOGGER = Logger.getLogger(MediaServiceImpl.class);

    @Resource
    private MediaDao mediaDao;

    @Resource
    private RemoteBehaviorService remoteBehaviorService;

    @Override
    public List<Media> getLastImageByUserId(Integer userId) throws ServiceException {
        if (userId == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        List<Media> result = null;
        try {
            // 通过嘀嘀号查询用户最新一次请求
            RemoteBehavior remoteBehavior = remoteBehaviorService.getRemoteBehaviorConsummate(userId);
            if (remoteBehavior != null) {
                result = mediaDao.getMediaesByRemoteId(remoteBehavior.getId());
            }
        } catch (com.glsx.cloudframework.exception.ServiceException e) {
            LOGGER.warn("===Get_remoteBehavior_error===" + e.getMessage() + "_code=" + e.getCode());
        }

        return result;
    }

    @Override
    public Pagination getMediaes(MediaSearch mediaSearch, Pagination page) throws ServiceException {
        if (mediaSearch == null || mediaSearch.getUserId() == null || mediaSearch.getMediaType() == null || page == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        Pagination resPage = mediaDao.getMediaesForType(mediaSearch, page);
        if (resPage != null && resPage.getTotalPage() < page.getPageNo()) {
            resPage.setList(null);
        }

        return resPage;
    }

    @Override
    public void saveMedia(Media media) throws ServiceException {
        if (media == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        if (!StringUtils.isEmpty(media.getGpsLat()) && !StringUtils.isEmpty(media.getGpsLong())) {
            try {
                JSONObject result = ApacheHttpUtils.gps2Address(media.getGpsLat(), media.getGpsLong());
                if (null != result) {
                    String activeAddress = result.getString("formatted_address");
                    media.setImageAddress(activeAddress);
                }
            } catch (Exception e) {}
        }

        media.setStatus(2);
        mediaDao.save(media);
    }

    @Override
    public List<Media> getMediaesByMediaId(Integer mediaId) throws ServiceException {
        if (mediaId == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        List<Media> result = null;
        try {
            RemoteBehavior remoteBehavior = remoteBehaviorService.getRemoteBehaviorByMediaId(String.valueOf(mediaId));
            if (remoteBehavior != null) {
                result = mediaDao.getMediaesByRemoteId(remoteBehavior.getId());
            }
        } catch (com.glsx.cloudframework.exception.ServiceException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteMedia(Integer id) throws ServiceException {
        if (id == null) {
            throw new ServiceException(ServiceExceptionType.CODE_PARAMETER, ServiceExceptionType.MSG_PARAMETER);
        }

        mediaDao.deleteMedia(id);
    }

    @Override
    public List<Media> getMediaesByBehaviorId(Integer behaviorId) {

        return mediaDao.getMediaesByRemoteId(behaviorId);
    }
}