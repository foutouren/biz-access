package com.glsx.biz.access.container.dao.access;

import com.glsx.biz.access.common.entity.RemoteBehavior;
import com.glsx.biz.access.common.entity.enums.ReciverStatus;
import com.glsx.biz.access.common.entity.enums.ReqSource;
import com.glsx.biz.access.container.dao.base.BaseDAO;
import com.glsx.biz.access.container.utils.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RemoteBehaviorDao extends BaseDAO {

    public RemoteBehavior getRemoteBehaviorByMediaId(String mediaId) {
        StringBuffer hql = new StringBuffer();
        Map<String, Object> param = new HashMap<String, Object>();
        hql.append(" from RemoteBehavior where mediaId=:mediaId");
        param.put("mediaId",mediaId);
        return (RemoteBehavior) this.hibernateBaseDAO.findOne(hql.toString(), param);
    }

    public RemoteBehavior getRemoteBehaviorByUserId(Integer userId, String mediaId) {
        StringBuffer hql = new StringBuffer();
        Map<String, Object> param = new HashMap<String, Object>();
        hql.append(" from RemoteBehavior where userId=:userId and mediaId=:mediaId");
        param.put("userId", userId);
        param.put("mediaId",mediaId);
        return (RemoteBehavior) this.hibernateBaseDAO.findOne(hql.toString(), param);
    }

    public RemoteBehavior getLastRemoteBehavior(Integer userId) {
        StringBuffer hql = new StringBuffer();
        Map<String, Object> param = new HashMap<String, Object>();
        hql.append(" from RemoteBehavior where source!=3 and userId=:userId order by createTime desc");
        param.put("userId",userId);
        return (RemoteBehavior) this.hibernateBaseDAO.findOne(hql.toString(), param);
    }

    public RemoteBehavior getRemoteBehaviorLastByIMEI(String imei) {
        StringBuffer hql = new StringBuffer();
        String lastDate = DateUtils.minusSeconds(new Date(), -1);

        Map<String, Object> param = new HashMap<String, Object>();
        hql.append(" from RemoteBehavior where source=3 and imei=:imei order by createTime desc");
        param.put("imei",imei);
        return (RemoteBehavior) this.hibernateBaseDAO.findOne(hql.toString(), param);
    }

    public void updateReciverStatus(Integer id, ReciverStatus status) {
        Map<String, Object> param = new HashMap<String, Object>();
        String hsql = "update RemoteBehavior set reciverStatus=:reciverStatus,updateTime=NOW() where id=:id";

        param.put("id", id);
        param.put("reciverStatus", status);

        this.hibernateBaseDAO.update(hsql, param);
    }

    public RemoteBehavior getRemoteBehaviorConsummate(Integer userId, ReciverStatus reciverStatus) {
        StringBuffer hql = new StringBuffer();
        Map<String, Object> param = new HashMap<String, Object>();
        hql.append(" from RemoteBehavior where userId=:userId and operateType=1 and source!=3 and status=1 and reciverStatus=:reciverStatus order by createTime desc");
        param.put("userId",userId);
        param.put("reciverStatus", reciverStatus);
        return (RemoteBehavior) this.hibernateBaseDAO.findOne(hql.toString(), param);
    }
}
