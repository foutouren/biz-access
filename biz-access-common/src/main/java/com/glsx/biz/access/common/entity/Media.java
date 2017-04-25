package com.glsx.biz.access.common.entity;

import com.glsx.biz.access.common.entity.base.BaseEntity;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/14
 * Time: 14:40
 *
 * Description: 远程操作多媒体数据
 */
public class Media extends BaseEntity {

    private Integer id;// 主键
    private Integer userId;// 嘀嘀号
    private Integer mediaType;// 媒体类型：1图片，2视频
    private String mediaUrl;// 多媒体源地址url
    private String smallUrl;// 缩略图url
    private String gpsLong;// 经度
    private String gpsLat;// 纬度
    private Integer reportReason;// 上报原因：1停车抓拍，2用户远程指令操作，3碰撞拍摄
    private Integer behaviorId;// 操作id，对应platform_access_remote_behavior表的主键
    private Integer mirroType;// 后视镜类型：1前置，2后置
    private Date mediaDate;// 媒体数据生成时间

    private String imageAddress;// 图片所在地址
    private Integer status;//状态：2表示正常，-9表示删除

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

    public Integer getMediaType() {
        return mediaType;
    }

    public void setMediaType(Integer mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getGpsLong() {
        return gpsLong;
    }

    public void setGpsLong(String gpsLong) {
        this.gpsLong = gpsLong;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public Integer getReportReason() {
        return reportReason;
    }

    public void setReportReason(Integer reportReason) {
        this.reportReason = reportReason;
    }

    public Integer getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(Integer behaviorId) {
        this.behaviorId = behaviorId;
    }

    public Integer getMirroType() {
        return mirroType;
    }

    public void setMirroType(Integer mirroType) {
        this.mirroType = mirroType;
    }

    public Date getMediaDate() {
        return mediaDate;
    }

    public void setMediaDate(Date mediaDate) {
        this.mediaDate = mediaDate;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Media media = (Media) o;

        if (id != null ? !id.equals(media.id) : media.id != null) return false;
        if (userId != null ? !userId.equals(media.userId) : media.userId != null) return false;
        if (mediaType != null ? !mediaType.equals(media.mediaType) : media.mediaType != null) return false;
        if (mediaUrl != null ? !mediaUrl.equals(media.mediaUrl) : media.mediaUrl != null) return false;
        if (smallUrl != null ? !smallUrl.equals(media.smallUrl) : media.smallUrl != null) return false;
        if (gpsLong != null ? !gpsLong.equals(media.gpsLong) : media.gpsLong != null) return false;
        if (gpsLat != null ? !gpsLat.equals(media.gpsLat) : media.gpsLat != null) return false;
        if (reportReason != null ? !reportReason.equals(media.reportReason) : media.reportReason != null) return false;
        if (behaviorId != null ? !behaviorId.equals(media.behaviorId) : media.behaviorId != null) return false;
        if (mirroType != null ? !mirroType.equals(media.mirroType) : media.mirroType != null) return false;
        if (mediaDate != null ? !mediaDate.equals(media.mediaDate) : media.mediaDate != null) return false;
        if (imageAddress != null ? !imageAddress.equals(media.imageAddress) : media.imageAddress != null) return false;
        return status != null ? status.equals(media.status) : media.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (mediaType != null ? mediaType.hashCode() : 0);
        result = 31 * result + (mediaUrl != null ? mediaUrl.hashCode() : 0);
        result = 31 * result + (smallUrl != null ? smallUrl.hashCode() : 0);
        result = 31 * result + (gpsLong != null ? gpsLong.hashCode() : 0);
        result = 31 * result + (gpsLat != null ? gpsLat.hashCode() : 0);
        result = 31 * result + (reportReason != null ? reportReason.hashCode() : 0);
        result = 31 * result + (behaviorId != null ? behaviorId.hashCode() : 0);
        result = 31 * result + (mirroType != null ? mirroType.hashCode() : 0);
        result = 31 * result + (mediaDate != null ? mediaDate.hashCode() : 0);
        result = 31 * result + (imageAddress != null ? imageAddress.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", userId=" + userId +
                ", mediaType=" + mediaType +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", smallUrl='" + smallUrl + '\'' +
                ", gpsLong='" + gpsLong + '\'' +
                ", gpsLat='" + gpsLat + '\'' +
                ", reportReason=" + reportReason +
                ", behaviorId=" + behaviorId +
                ", mirroType=" + mirroType +
                ", mediaDate=" + mediaDate +
                ", imageAddress='" + imageAddress + '\'' +
                ", status=" + status +
                '}';
    }
}