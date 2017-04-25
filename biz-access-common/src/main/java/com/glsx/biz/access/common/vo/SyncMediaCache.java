package com.glsx.biz.access.common.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2017/3/1
 * Time: 13:42
 * <p>
 * Description: 同步图片数据缓存
 */
public class SyncMediaCache implements Serializable {

    private String imei;
    private Integer preState = 1; // 1表示未收到；2表示已收到
    private Integer backState = 1; // 1表示未收到；2表示已收到
    private String lastDate;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getPreState() {
        return preState;
    }

    public void setPreState(Integer preState) {
        this.preState = preState;
    }

    public Integer getBackState() {
        return backState;
    }

    public void setBackState(Integer backState) {
        this.backState = backState;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SyncMediaCache that = (SyncMediaCache) o;

        if (imei != null ? !imei.equals(that.imei) : that.imei != null) return false;
        if (preState != null ? !preState.equals(that.preState) : that.preState != null) return false;
        if (backState != null ? !backState.equals(that.backState) : that.backState != null) return false;
        return lastDate != null ? lastDate.equals(that.lastDate) : that.lastDate == null;
    }

    @Override
    public int hashCode() {
        int result = imei != null ? imei.hashCode() : 0;
        result = 31 * result + (preState != null ? preState.hashCode() : 0);
        result = 31 * result + (backState != null ? backState.hashCode() : 0);
        result = 31 * result + (lastDate != null ? lastDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SyncMediaCache{" +
                "imei='" + imei + '\'' +
                ", preState=" + preState +
                ", backState=" + backState +
                ", lastDate='" + lastDate + '\'' +
                '}';
    }
}