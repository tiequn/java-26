package com.kaishengit.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author guojiabang
 * @date 2018/7/14 0014
 */
public class BaseService {

    private Integer id;
    private String name;
    private Double score;
    private List<String> strList;
    private Set<Integer> numSets;
    private Map<String, String> maps;
    private Properties properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public Set<Integer> getNumSets() {
        return numSets;
    }

    public void setNumSets(Set<Integer> numSets) {
        this.numSets = numSets;
    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
