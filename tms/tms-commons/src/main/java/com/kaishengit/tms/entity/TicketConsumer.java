package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class TicketConsumer implements Serializable {
    /**
     * ID
     */
    private Long id;

    private Long ticketId;

    private Integer scenicAccountId;

    private Date createTime;

    private Date updateTime;

    /**
     * 是否已经结算
     */
    private Byte settlement;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getScenicAccountId() {
        return scenicAccountId;
    }

    public void setScenicAccountId(Integer scenicAccountId) {
        this.scenicAccountId = scenicAccountId;
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

    public Byte getSettlement() {
        return settlement;
    }

    public void setSettlement(Byte settlement) {
        this.settlement = settlement;
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
        TicketConsumer other = (TicketConsumer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()))
            && (this.getScenicAccountId() == null ? other.getScenicAccountId() == null : this.getScenicAccountId().equals(other.getScenicAccountId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getSettlement() == null ? other.getSettlement() == null : this.getSettlement().equals(other.getSettlement()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        result = prime * result + ((getScenicAccountId() == null) ? 0 : getScenicAccountId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getSettlement() == null) ? 0 : getSettlement().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", scenicAccountId=").append(scenicAccountId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", settlement=").append(settlement);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}