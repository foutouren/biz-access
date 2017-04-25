package com.glsx.biz.access.container.dao.access;

import com.glsx.biz.access.common.entity.Media;
import com.glsx.biz.access.common.vo.MediaSearch;
import com.glsx.biz.access.container.dao.base.BaseDAO;
import com.glsx.cloudframework.core.datastructure.page.Pagination;
import com.glsx.cloudframework.persistent.types.Finder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MediaDao extends BaseDAO {

    public Media getLastMediaForPhoto(Integer userId) {
        StringBuffer hql = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		hql.append(" from Media where status=2 and userId=:userId and mediaType=1 order by createTime desc");
		param.put("userId",userId);
		return (Media) this.hibernateBaseDAO.findOne(hql.toString(), param);
    }

    public Pagination getMediaesForType(MediaSearch mediaSearch, Pagination page) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from Media where status=2 and userId=");
        hql.append(mediaSearch.getUserId());
        hql.append(" and mediaType=");
        hql.append(mediaSearch.getMediaType());
        if (mediaSearch.getReportReason() != null) {
            hql.append(" and reportReason=");
            hql.append(mediaSearch.getReportReason());
        }
        hql.append(" order by createTime desc");

        return (Pagination) this.hibernateBaseDAO.find(Finder.create(hql.toString()), page.getNextPage(), page.getPageSize());
    }

    public List<Media> getMediaesByRemoteId(Integer remoteId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from Media where status=2 and behaviorId=");
        hql.append(remoteId);
        hql.append(" order by createTime desc");

        return (List<Media>) this.hibernateBaseDAO.find(hql.toString(), null);
    }

    public void deleteMedia(Integer id) {
        Map<String, Object> param = new HashMap<String, Object>();
        String hql = "update Media set status=-9,updateTime=NOW() where id=:id";
        param.put("id", id);
        this.hibernateBaseDAO.update(hql, param);
    }
}
