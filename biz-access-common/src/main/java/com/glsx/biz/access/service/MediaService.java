package com.glsx.biz.access.service;

import com.glsx.biz.access.common.entity.Media;
import com.glsx.biz.access.common.vo.MediaSearch;
import com.glsx.cloudframework.core.datastructure.page.Pagination;
import com.glsx.cloudframework.core.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/14
 * Time: 15:54
 * <p>
 * Description: 多媒体数据服务接口
 */
public interface MediaService {

    /**
     * 通过嘀嘀号获取最新的拍照信息
     * @param userId    嘀嘀号
     * @return 返回最新的一条图片数据
     * @throws ServiceException
     */
    public List<Media> getLastImageByUserId(Integer userId) throws ServiceException;

    /**
     * 获取图片或者视频列表
     * @param mediaSearch 多媒体搜索
     * @param page 分页数据
     * @return 返回多媒体数据列表
     * @throws ServiceException
     */
    public Pagination getMediaes(MediaSearch mediaSearch, Pagination page) throws ServiceException;

    /**
     * 保存用户多媒体数据（拍照+视频）
     * @param media
     * @hrows ServiceException
     */
    public void saveMedia(Media media) throws ServiceException;

    /**
     * 通过mediaid获取多媒体的数据
     * @param mediaId
     * @return
     * @throws ServiceException
     */
    public List<Media> getMediaesByMediaId(Integer mediaId) throws ServiceException;

    /**
     * 通过主键删除图片数据
     * @param id
     * @throws ServiceException
     */
    public void deleteMedia(Integer id) throws ServiceException;

    /**
     * 通过behaviorid获取多媒体数据
     * @param behaviorId
     * @return
     * @throws ServiceException
     */
    public List<Media> getMediaesByBehaviorId(Integer behaviorId) ;
}
