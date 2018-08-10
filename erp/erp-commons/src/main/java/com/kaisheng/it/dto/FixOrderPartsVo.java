package com.kaisheng.it.dto;

import com.kaisheng.it.entity.FixOrderParts;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/8/10 0010
 */
public class FixOrderPartsVo {

    private Integer employeeId;
    private List<FixOrderParts> fixOrderPartsList;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public List<FixOrderParts> getFixOrderPartsList() {
        return fixOrderPartsList;
    }

    public void setFixOrderPartsList(List<FixOrderParts> fixOrderPartsList) {
        this.fixOrderPartsList = fixOrderPartsList;
    }
}
