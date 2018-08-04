package com.kaisheng.it.vo;

/**
 * @author guojiabang
 * @date 2018/8/4 0004
 */
public class PartsVo {

    private Integer id;
    private Integer num;

    @Override
    public String toString() {
        return "PartsVo{" +
                "id=" + id +
                ", num=" + num +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
