package com.glsx.biz.access.common.entity;

import com.glsx.biz.access.common.entity.base.BaseEntity;
import com.glsx.biz.access.common.entity.enums.*;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/14
 * Time: 15:01
 * <p>
 * Description: 拍照+视频远程操作日志
 */
public class RemoteBehavior extends BaseEntity {

    private Integer id;// 主键
    private Integer userId;// 嘀嘀号
    private Operate operateType;// 操作类型：1拍照，2视频
    private MirroPosition operateValue = MirroPosition.MIRRO_POSITION_DEFAULT;// 操作值：1前后视镜，2后后视镜，3前后后视镜
    private Integer videoTime;// 录视频的时长（秒）
    private String mediaId;// 媒体操作唯一标示id
    private String imei;// 设备的imei
    private RHCStatus status = RHCStatus.RHC_STATUS_DEFAULT;// 数据状态，默认0，1已发送指令，2拍照完成，3发送指令失败。
    private ReqSource source;// 请求来源：1微信，2驾宝  3 为增加的类型，表示碰撞上报的数据记录
    private String reqResult;// 调用第三方接口的返回结果
    private ReciverStatus reciverStatus = ReciverStatus.MEDIA_UN_RECIVER; // 数据接收状态：默认为1未接收，2已接收。

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Operate getOperateType() {
        return operateType;
    }

    public void setOperateType(Operate operateType) {
        this.operateType = operateType;
    }

    public MirroPosition getOperateValue() {
        return operateValue;
    }

    public void setOperateValue(MirroPosition operateValue) {
        this.operateValue = operateValue;
    }

    public Integer getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Integer videoTime) {
        this.videoTime = videoTime;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public RHCStatus getStatus() {
        return status;
    }

    public void setStatus(RHCStatus status) {
        this.status = status;
    }

    public ReqSource getSource() {
        return source;
    }

    public void setSource(ReqSource source) {
        this.source = source;
    }

    public String getReqResult() {
        return reqResult;
    }

    public void setReqResult(String reqResult) {
        this.reqResult = reqResult;
    }

    public ReciverStatus getReciverStatus() {
        return reciverStatus;
    }

    public void setReciverStatus(ReciverStatus reciverStatus) {
        this.reciverStatus = reciverStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemoteBehavior that = (RemoteBehavior) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (operateType != that.operateType) return false;
        if (operateValue != that.operateValue) return false;
        if (videoTime != null ? !videoTime.equals(that.videoTime) : that.videoTime != null) return false;
        if (mediaId != null ? !mediaId.equals(that.mediaId) : that.mediaId != null) return false;
        if (imei != null ? !imei.equals(that.imei) : that.imei != null) return false;
        if (status != that.status) return false;
        if (source != that.source) return false;
        if (reqResult != null ? !reqResult.equals(that.reqResult) : that.reqResult != null) return false;
        return reciverStatus == that.reciverStatus;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (operateType != null ? operateType.hashCode() : 0);
        result = 31 * result + (operateValue != null ? operateValue.hashCode() : 0);
        result = 31 * result + (videoTime != null ? videoTime.hashCode() : 0);
        result = 31 * result + (mediaId != null ? mediaId.hashCode() : 0);
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (reqResult != null ? reqResult.hashCode() : 0);
        result = 31 * result + (reciverStatus != null ? reciverStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RemoteBehavior{" +
                "id=" + id +
                ", userId=" + userId +
                ", operateType=" + operateType +
                ", operateValue=" + operateValue +
                ", videoTime=" + videoTime +
                ", mediaId='" + mediaId + '\'' +
                ", imei='" + imei + '\'' +
                ", status=" + status +
                ", source=" + source +
                ", reqResult='" + reqResult + '\'' +
                ", reciverStatus=" + reciverStatus +
                '}';
    }
}