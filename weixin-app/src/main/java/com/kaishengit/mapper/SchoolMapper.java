package com.kaishengit.mapper;

import com.kaishengit.entity.School;
import org.apache.ibatis.annotations.Select;

/**
 * @author guojiabang
 * @date 2018/7/13 0013
 */
public interface SchoolMapper {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    School findBySchoolId(Integer id);



}
