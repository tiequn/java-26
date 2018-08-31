package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class ScenicAccount implements Serializable {
    private Integer id;

    /**
     * 景区登录账号
     */
    private String scenicAccount;

    /**
     * 景区密码
     */
    private String scenicPassword;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 登录时间
     */
    private Date updateTime;

    /**
     * 景区状态
     */
    private String scenicState;

    private Integer scenicId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScenicAccount() {
        return scenicAccount;
    }

    public void setScenicAccount(String scenicAccount) {
        this.scenicAccount = scenicAccount;
    }

    public String getScenicPassword() {
        return scenicPassword;
    }

    public void setScenicPassword(String scenicPassword) {
        this.scenicPassword = scenicPassword;
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

    public String getScenicState() {
        return scenicState;
    }

    public void setScenicState(String scenicState) {
        this.scenicState = scenicState;
    }

    public Integer getScenicId() {
        return scenicId;
    }

    public void setScenicId(Integer scenicId) {
        this.scenicId = scenicId;
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
        ScenicAccount other = (ScenicAccount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getScenicAccount() == null ? other.getScenicAccount() == null : this.getScenicAccount().equals(other.getScenicAccount()))
            && (this.getScenicPassword() == null ? other.getScenicPassword() == null : this.getScenicPassword().equals(other.getScenicPassword()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getScenicState() == null ? other.getScenicState() == null : this.getScenicState().equals(other.getScenicState()))
            && (this.getScenicId() == null ? other.getScenicId() == null : this.getScenicId().equals(other.getScenicId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getScenicAccount() == null) ? 0 : getScenicAccount().hashCode());
        result = prime * result + ((getScenicPassword() == null) ? 0 : getScenicPassword().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getScenicState() == null) ? 0 : getScenicState().hashCode());
        result = prime * result + ((getScenicId() == null) ? 0 : getScenicId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", scenicAccount=").append(scenicAccount);
        sb.append(", scenicPassword=").append(scenicPassword);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", scenicState=").append(scenicState);
        sb.append(", scenicId=").append(scenicId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}