package com.glsx.biz.access.common.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2017/3/1
 * Time: 11:19
 * <p>
 * Description: 同步拍照数据
 */
public class SyncMedia implements Serializable {

    private Integer userid;
    private String imei;// 设备唯一识别码（针对小镜用户采用）
    private Integer appfromtype;// 应用来源：1、微信，2嘀嘀虎app
    private String mediaid;// 媒体操作流水号
    private String data;// 多媒体xml
    private String longi;// 经度
    private String late;// 纬度
    private Integer reportreason;//上报原因：1停车抓拍，2用户远程指令操作，3碰撞拍摄
    private String createtime; // 媒体生成时间

    public SyncMedia() {
    }

    public SyncMedia(Integer userid, String imei, Integer appfromtype, String mediaid, String data, String longi, String late, Integer reportreason, String createtime) {
        this.userid = userid;
        this.imei = imei;
        this.appfromtype = appfromtype;
        this.mediaid = mediaid;
        this.data = data;
        this.longi = longi;
        this.late = late;
        this.reportreason = reportreason;
        this.createtime = createtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getAppfromtype() {
        return appfromtype;
    }

    public void setAppfromtype(Integer appfromtype) {
        this.appfromtype = appfromtype;
    }

    public String getMediaid() {
        return mediaid;
    }

    public void setMediaid(String mediaid) {
        this.mediaid = mediaid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public Integer getReportreason() {
        return reportreason;
    }

    public void setReportreason(Integer reportreason) {
        this.reportreason = reportreason;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "SyncMedia{" +
                "userid=" + userid +
                ", imei='" + imei + '\'' +
                ", appfromtype=" + appfromtype +
                ", mediaid='" + mediaid + '\'' +
                ", data='" + data + '\'' +
                ", longi='" + longi + '\'' +
                ", late='" + late + '\'' +
                ", reportreason=" + reportreason +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}