package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Scenic implements Serializable {
    private Integer id;

    /**
     * 景区名称
     */
    private String scenicName;

    /**
     * 景区星级 例如 5A景区
     */
    private String scenicLevel;

    /**
     * 景区地址
     */
    private String scenicAddress;

    /**
     * 坐标_经度
     */
    private String scenicGeoLongitude;

    /**
     * 景区坐标_纬度
     */
    private String scenicGeoLatitude;

    /**
     * 景区联系人
     */
    private String scenicManager;

    /**
     * 景区联系电话
     */
    private String scenicTel;

    private Date createTime;

    private Date updateTime;

    /**
     * 景区营业执照照片
     */
    private String scenicAttachment;

    private Integer scenicAccountId;

    /**
     * 景区介绍
     */
    private String scenicIntro;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getScenicLevel() {
        return scenicLevel;
    }

    public void setScenicLevel(String scenicLevel) {
        this.scenicLevel = scenicLevel;
    }

    public String getScenicAddress() {
        return scenicAddress;
    }

    public void setScenicAddress(String scenicAddress) {
        this.scenicAddress = scenicAddress;
    }

    public String getScenicGeoLongitude() {
        return scenicGeoLongitude;
    }

    public void setScenicGeoLongitude(String scenicGeoLongitude) {
        this.scenicGeoLongitude = scenicGeoLongitude;
    }

    public String getScenicGeoLatitude() {
        return scenicGeoLatitude;
    }

    public void setScenicGeoLatitude(String scenicGeoLatitude) {
        this.scenicGeoLatitude = scenicGeoLatitude;
    }

    public String getScenicManager() {
        return scenicManager;
    }

    public void setScenicManager(String scenicManager) {
        this.scenicManager = scenicManager;
    }

    public String getScenicTel() {
        return scenicTel;
    }

    public void setScenicTel(String scenicTel) {
        this.scenicTel = scenicTel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getScenicAttachment() {
        return scenicAttachment;
    }

    public void setScenicAttachment(String scenicAttachment) {
        this.scenicAttachment = scenicAttachment;
    }

    public Integer getScenicAccountId() {
        return scenicAccountId;
    }

    public void setScenicAccountId(Integer scenicAccountId) {
        this.scenicAccountId = scenicAccountId;
    }

    public String getScenicIntro() {
        return scenicIntro;
    }

    public void setScenicIntro(String scenicIntro) {
        this.scenicIntro = scenicIntro;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Scenic other = (Scenic) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getScenicName() == null ? other.getScenicName() == null : this.getScenicName().equals(other.getScenicName()))
            && (this.getScenicLevel() == null ? other.getScenicLevel() == null : this.getScenicLevel().equals(other.getScenicLevel()))
            && (this.getScenicAddress() == null ? other.getScenicAddress() == null : this.getScenicAddress().equals(other.getScenicAddress()))
            && (this.getScenicGeoLongitude() == null ? other.getScenicGeoLongitude() == null : this.getScenicGeoLongitude().equals(other.getScenicGeoLongitude()))
            && (this.getScenicGeoLatitude() == null ? other.getScenicGeoLatitude() == null : this.getScenicGeoLatitude().equals(other.getScenicGeoLatitude()))
            && (this.getScenicManager() == null ? other.getScenicManager() == null : this.getScenicManager().equals(other.getScenicManager()))
            && (this.getScenicTel() == null ? other.getScenicTel() == null : this.getScenicTel().equals(other.getScenicTel()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getScenicAttachment() == null ? other.getScenicAttachment() == null : this.getScenicAttachment().equals(other.getScenicAttachment()))
            && (this.getScenicAccountId() == null ? other.getScenicAccountId() == null : this.getScenicAccountId().equals(other.getScenicAccountId()))
            && (this.getScenicIntro() == null ? other.getScenicIntro() == null : this.getScenicIntro().equals(other.getScenicIntro()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getScenicName() == null) ? 0 : getScenicName().hashCode());
        result = prime * result + ((getScenicLevel() == null) ? 0 : getScenicLevel().hashCode());
        result = prime * result + ((getScenicAddress() == null) ? 0 : getScenicAddress().hashCode());
        result = prime * result + ((getScenicGeoLongitude() == null) ? 0 : getScenicGeoLongitude().hashCode());
        result = prime * result + ((getScenicGeoLatitude() == null) ? 0 : getScenicGeoLatitude().hashCode());
        result = prime * result + ((getScenicManager() == null) ? 0 : getScenicManager().hashCode());
        result = prime * result + ((getScenicTel() == null) ? 0 : getScenicTel().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getScenicAttachment() == null) ? 0 : getScenicAttachment().hashCode());
        result = prime * result + ((getScenicAccountId() == null) ? 0 : getScenicAccountId().hashCode());
        result = prime * result + ((getScenicIntro() == null) ? 0 : getScenicIntro().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", scenicName=").append(scenicName);
        sb.append(", scenicLevel=").append(scenicLevel);
        sb.append(", scenicAddress=").append(scenicAddress);
        sb.append(", scenicGeoLongitude=").append(scenicGeoLongitude);
        sb.append(", scenicGeoLatitude=").append(scenicGeoLatitude);
        sb.append(", scenicManager=").append(scenicManager);
        sb.append(", scenicTel=").append(scenicTel);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", scenicAttachment=").append(scenicAttachment);
        sb.append(", scenicAccountId=").append(scenicAccountId);
        sb.append(", scenicIntro=").append(scenicIntro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}