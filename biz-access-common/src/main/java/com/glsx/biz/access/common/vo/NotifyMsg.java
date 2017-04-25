package com.glsx.biz.access.common.vo;

import java.io.Serializable;

/**
 * 多媒体数据查询
 * Created by Lenovo on 2016/8/17.
 */
public class NotifyMsg implements Serializable {

    private Integer userId; // 嘀嘀号
    private Integer mediaType;// 媒体类型：1图片，2视频
    private String mediaId;// 唯一操作id
    private Integer behaviorId;// 关联的操作id
    private Integer sourceId;// 来源id：对应behavior中的source
    private Integer reportReason;// 上报类型：1停车抓拍，2用户远程指令操作，3碰撞拍摄

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Integer getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(Integer behaviorId) {
        this.behaviorId = behaviorId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getReportReason() {
        return reportReason;
    }

    public void setReportReason(Integer reportReason) {
        this.reportReason = reportReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotifyMsg notifyMsg = (NotifyMsg) o;

        if (userId != null ? !userId.equals(notifyMsg.userId) : notifyMsg.userId != null) return false;
        if (mediaType != null ? !mediaType.equals(notifyMsg.mediaType) : notifyMsg.mediaType != null) return false;
        if (mediaId != null ? !mediaId.equals(notifyMsg.mediaId) : notifyMsg.mediaId != null) return false;
        if (behaviorId != null ? !behaviorId.equals(notifyMsg.behaviorId) : notifyMsg.behaviorId != null) return false;
        if (sourceId != null ? !sourceId.equals(notifyMsg.sourceId) : notifyMsg.sourceId != null) return false;
        return reportReason != null ? reportReason.equals(notifyMsg.reportReason) : notifyMsg.reportReason == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
        result = 31 * result + (mediaId != null ? mediaId.hashCode() : 0);
        result = 31 * result + (behaviorId != null ? behaviorId.hashCode() : 0);
        result = 31 * result + (sourceId != null ? sourceId.hashCode() : 0);
        result = 31 * result + (reportReason != null ? reportReason.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NotifyMsg{" +
                "userId=" + userId +
                ", mediaType=" + mediaType +
                ", mediaId='" + mediaId + '\'' +
                ", behaviorId=" + behaviorId +
                ", sourceId=" + sourceId +
                ", reportReason=" + reportReason +
                '}';
    }
}
