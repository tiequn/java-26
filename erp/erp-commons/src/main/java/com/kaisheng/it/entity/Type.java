package com.kaisheng.it.entity;

import java.io.Serializable;

public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}