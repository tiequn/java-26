package com.kaishengit.tms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScenicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ScenicExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andScenicNameIsNull() {
            addCriterion("scenic_name is null");
            return (Criteria) this;
        }

        public Criteria andScenicNameIsNotNull() {
            addCriterion("scenic_name is not null");
            return (Criteria) this;
        }

        public Criteria andScenicNameEqualTo(String value) {
            addCriterion("scenic_name =", value, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameNotEqualTo(String value) {
            addCriterion("scenic_name <>", value, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameGreaterThan(String value) {
            addCriterion("scenic_name >", value, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_name >=", value, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameLessThan(String value) {
            addCriterion("scenic_name <", value, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameLessThanOrEqualTo(String value) {
            addCriterion("scenic_name <=", value, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameLike(String value) {
            addCriterion("scenic_name like", value, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameNotLike(String value) {
            addCriterion("scenic_name not like", value, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameIn(List<String> values) {
            addCriterion("scenic_name in", values, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameNotIn(List<String> values) {
            addCriterion("scenic_name not in", values, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameBetween(String value1, String value2) {
            addCriterion("scenic_name between", value1, value2, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicNameNotBetween(String value1, String value2) {
            addCriterion("scenic_name not between", value1, value2, "scenicName");
            return (Criteria) this;
        }

        public Criteria andScenicLevelIsNull() {
            addCriterion("scenic_level is null");
            return (Criteria) this;
        }

        public Criteria andScenicLevelIsNotNull() {
            addCriterion("scenic_level is not null");
            return (Criteria) this;
        }

        public Criteria andScenicLevelEqualTo(String value) {
            addCriterion("scenic_level =", value, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelNotEqualTo(String value) {
            addCriterion("scenic_level <>", value, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelGreaterThan(String value) {
            addCriterion("scenic_level >", value, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_level >=", value, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelLessThan(String value) {
            addCriterion("scenic_level <", value, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelLessThanOrEqualTo(String value) {
            addCriterion("scenic_level <=", value, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelLike(String value) {
            addCriterion("scenic_level like", value, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelNotLike(String value) {
            addCriterion("scenic_level not like", value, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelIn(List<String> values) {
            addCriterion("scenic_level in", values, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelNotIn(List<String> values) {
            addCriterion("scenic_level not in", values, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelBetween(String value1, String value2) {
            addCriterion("scenic_level between", value1, value2, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicLevelNotBetween(String value1, String value2) {
            addCriterion("scenic_level not between", value1, value2, "scenicLevel");
            return (Criteria) this;
        }

        public Criteria andScenicAddressIsNull() {
            addCriterion("scenic_address is null");
            return (Criteria) this;
        }

        public Criteria andScenicAddressIsNotNull() {
            addCriterion("scenic_address is not null");
            return (Criteria) this;
        }

        public Criteria andScenicAddressEqualTo(String value) {
            addCriterion("scenic_address =", value, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressNotEqualTo(String value) {
            addCriterion("scenic_address <>", value, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressGreaterThan(String value) {
            addCriterion("scenic_address >", value, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_address >=", value, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressLessThan(String value) {
            addCriterion("scenic_address <", value, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressLessThanOrEqualTo(String value) {
            addCriterion("scenic_address <=", value, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressLike(String value) {
            addCriterion("scenic_address like", value, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressNotLike(String value) {
            addCriterion("scenic_address not like", value, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressIn(List<String> values) {
            addCriterion("scenic_address in", values, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressNotIn(List<String> values) {
            addCriterion("scenic_address not in", values, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressBetween(String value1, String value2) {
            addCriterion("scenic_address between", value1, value2, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicAddressNotBetween(String value1, String value2) {
            addCriterion("scenic_address not between", value1, value2, "scenicAddress");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeIsNull() {
            addCriterion("scenic_geo_longitude is null");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeIsNotNull() {
            addCriterion("scenic_geo_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeEqualTo(String value) {
            addCriterion("scenic_geo_longitude =", value, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeNotEqualTo(String value) {
            addCriterion("scenic_geo_longitude <>", value, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeGreaterThan(String value) {
            addCriterion("scenic_geo_longitude >", value, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_geo_longitude >=", value, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeLessThan(String value) {
            addCriterion("scenic_geo_longitude <", value, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeLessThanOrEqualTo(String value) {
            addCriterion("scenic_geo_longitude <=", value, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeLike(String value) {
            addCriterion("scenic_geo_longitude like", value, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeNotLike(String value) {
            addCriterion("scenic_geo_longitude not like", value, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeIn(List<String> values) {
            addCriterion("scenic_geo_longitude in", values, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeNotIn(List<String> values) {
            addCriterion("scenic_geo_longitude not in", values, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeBetween(String value1, String value2) {
            addCriterion("scenic_geo_longitude between", value1, value2, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLongitudeNotBetween(String value1, String value2) {
            addCriterion("scenic_geo_longitude not between", value1, value2, "scenicGeoLongitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeIsNull() {
            addCriterion("scenic_geo_latitude is null");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeIsNotNull() {
            addCriterion("scenic_geo_latitude is not null");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeEqualTo(String value) {
            addCriterion("scenic_geo_latitude =", value, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeNotEqualTo(String value) {
            addCriterion("scenic_geo_latitude <>", value, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeGreaterThan(String value) {
            addCriterion("scenic_geo_latitude >", value, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_geo_latitude >=", value, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeLessThan(String value) {
            addCriterion("scenic_geo_latitude <", value, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeLessThanOrEqualTo(String value) {
            addCriterion("scenic_geo_latitude <=", value, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeLike(String value) {
            addCriterion("scenic_geo_latitude like", value, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeNotLike(String value) {
            addCriterion("scenic_geo_latitude not like", value, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeIn(List<String> values) {
            addCriterion("scenic_geo_latitude in", values, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeNotIn(List<String> values) {
            addCriterion("scenic_geo_latitude not in", values, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeBetween(String value1, String value2) {
            addCriterion("scenic_geo_latitude between", value1, value2, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicGeoLatitudeNotBetween(String value1, String value2) {
            addCriterion("scenic_geo_latitude not between", value1, value2, "scenicGeoLatitude");
            return (Criteria) this;
        }

        public Criteria andScenicManagerIsNull() {
            addCriterion("scenic_manager is null");
            return (Criteria) this;
        }

        public Criteria andScenicManagerIsNotNull() {
            addCriterion("scenic_manager is not null");
            return (Criteria) this;
        }

        public Criteria andScenicManagerEqualTo(String value) {
            addCriterion("scenic_manager =", value, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerNotEqualTo(String value) {
            addCriterion("scenic_manager <>", value, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerGreaterThan(String value) {
            addCriterion("scenic_manager >", value, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_manager >=", value, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerLessThan(String value) {
            addCriterion("scenic_manager <", value, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerLessThanOrEqualTo(String value) {
            addCriterion("scenic_manager <=", value, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerLike(String value) {
            addCriterion("scenic_manager like", value, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerNotLike(String value) {
            addCriterion("scenic_manager not like", value, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerIn(List<String> values) {
            addCriterion("scenic_manager in", values, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerNotIn(List<String> values) {
            addCriterion("scenic_manager not in", values, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerBetween(String value1, String value2) {
            addCriterion("scenic_manager between", value1, value2, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicManagerNotBetween(String value1, String value2) {
            addCriterion("scenic_manager not between", value1, value2, "scenicManager");
            return (Criteria) this;
        }

        public Criteria andScenicTelIsNull() {
            addCriterion("scenic_tel is null");
            return (Criteria) this;
        }

        public Criteria andScenicTelIsNotNull() {
            addCriterion("scenic_tel is not null");
            return (Criteria) this;
        }

        public Criteria andScenicTelEqualTo(String value) {
            addCriterion("scenic_tel =", value, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelNotEqualTo(String value) {
            addCriterion("scenic_tel <>", value, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelGreaterThan(String value) {
            addCriterion("scenic_tel >", value, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_tel >=", value, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelLessThan(String value) {
            addCriterion("scenic_tel <", value, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelLessThanOrEqualTo(String value) {
            addCriterion("scenic_tel <=", value, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelLike(String value) {
            addCriterion("scenic_tel like", value, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelNotLike(String value) {
            addCriterion("scenic_tel not like", value, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelIn(List<String> values) {
            addCriterion("scenic_tel in", values, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelNotIn(List<String> values) {
            addCriterion("scenic_tel not in", values, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelBetween(String value1, String value2) {
            addCriterion("scenic_tel between", value1, value2, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andScenicTelNotBetween(String value1, String value2) {
            addCriterion("scenic_tel not between", value1, value2, "scenicTel");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentIsNull() {
            addCriterion("scenic_attachment is null");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentIsNotNull() {
            addCriterion("scenic_attachment is not null");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentEqualTo(String value) {
            addCriterion("scenic_attachment =", value, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentNotEqualTo(String value) {
            addCriterion("scenic_attachment <>", value, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentGreaterThan(String value) {
            addCriterion("scenic_attachment >", value, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentGreaterThanOrEqualTo(String value) {
            addCriterion("scenic_attachment >=", value, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentLessThan(String value) {
            addCriterion("scenic_attachment <", value, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentLessThanOrEqualTo(String value) {
            addCriterion("scenic_attachment <=", value, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentLike(String value) {
            addCriterion("scenic_attachment like", value, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentNotLike(String value) {
            addCriterion("scenic_attachment not like", value, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentIn(List<String> values) {
            addCriterion("scenic_attachment in", values, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentNotIn(List<String> values) {
            addCriterion("scenic_attachment not in", values, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentBetween(String value1, String value2) {
            addCriterion("scenic_attachment between", value1, value2, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAttachmentNotBetween(String value1, String value2) {
            addCriterion("scenic_attachment not between", value1, value2, "scenicAttachment");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdIsNull() {
            addCriterion("scenic_account_id is null");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdIsNotNull() {
            addCriterion("scenic_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdEqualTo(Integer value) {
            addCriterion("scenic_account_id =", value, "scenicAccountId");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdNotEqualTo(Integer value) {
            addCriterion("scenic_account_id <>", value, "scenicAccountId");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdGreaterThan(Integer value) {
            addCriterion("scenic_account_id >", value, "scenicAccountId");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("scenic_account_id >=", value, "scenicAccountId");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdLessThan(Integer value) {
            addCriterion("scenic_account_id <", value, "scenicAccountId");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdLessThanOrEqualTo(Integer value) {
            addCriterion("scenic_account_id <=", value, "scenicAccountId");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdIn(List<Integer> values) {
            addCriterion("scenic_account_id in", values, "scenicAccountId");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdNotIn(List<Integer> values) {
            addCriterion("scenic_account_id not in", values, "scenicAccountId");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdBetween(Integer value1, Integer value2) {
            addCriterion("scenic_account_id between", value1, value2, "scenicAccountId");
            return (Criteria) this;
        }

        public Criteria andScenicAccountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("scenic_account_id not between", value1, value2, "scenicAccountId");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}