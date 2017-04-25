package com.glsx.biz.access.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 多媒体数据查询
 * Created by Lenovo on 2016/8/17.
 */
public class MediaSearch implements Serializable {

    private Integer userId; // 嘀嘀号
    private Integer mediaType;// 媒体类型：1图片，2视频
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

        MediaSearch that = (MediaSearch) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (mediaType != null ? !mediaType.equals(that.mediaType) : that.mediaType != null) return false;
        return reportReason != null ? reportReason.equals(that.reportReason) : that.reportReason == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
        result = 31 * result + (reportReason != null ? reportReason.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MediaSearch{" +
                "userId=" + userId +
                ", mediaType=" + mediaType +
                ", reportReason=" + reportReason +
                '}';
    }
}
