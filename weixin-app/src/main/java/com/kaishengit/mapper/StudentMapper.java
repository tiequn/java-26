package com.kaishengit.mapper;


import com.kaishengit.entity.School;
import com.kaishengit.entity.Student;
import com.kaishengit.entity.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/11 0011
 */
public interface StudentMapper {

    /**
     * 批量插入
     * @param tagList
     * @return
     */
    int addBatch(@Param("tagList") List<Tag> tagList);

    Student findWithTagById(Integer id);
    Student findWithSchoolById(Integer id);
    School findStudentById(Integer id);


}
