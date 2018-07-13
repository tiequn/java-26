package com.kaishengit.mapper;


import com.kaishengit.entity.School;
import com.kaishengit.entity.Student;
import com.kaishengit.entity.Tag;
import org.apache.ibatis.annotations.*;

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

    // Student findWithTagById(Integer id);
    //Student findWithSchoolById(Integer id);
    School findStudentById(Integer id);

    /**
     *
     *
     * 注解形式 一对多
     *
     */
    @Select("select id, user_name, address, school_id from student where id = #{id}")
    @Results(
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "user_name", property = "userName"),
                    @Result(column = "address",property = "address"),
                    @Result(column = "school_id",property = "schoolId"),
                    @Result(column = "id", property = "tagList",
                            many = @Many(select = "com.kaishengit.mapper.TagMapper.findStudentById")

                    )

            }
    )
    Student findWithTagById(Integer id);

    /**
     * 注解形式
     * 一对一/一对多
     * @param id
     * @return
     */
    @Select("SELECT student.id, user_name, address, school_id, tag.id AS 'tag_id', tag_name " +
            "FROM student " +
            " WHERE student.id = #{id}")
    @Results(
        value = {
                @Result(column = "id", property = "id"),
                @Result(column = "user_name",property = "userName"),
                @Result(column = "address",property = "address"),
                @Result(column = "school_id",property = "schoolId"),
                @Result(column = "school_id", property = "school", one = @One(
                        select = "com.kaishengit.mapper.SchoolMapper.findById")
                )
        }

    )

    Student findWithSchoolById(Integer id);

}
