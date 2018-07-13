package com.kaishengit.mapper;

import com.kaishengit.entity.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author guojiabang
 * @date 2018/7/13 0013
 */
public interface TagMapper {

    @Select("select * from tag where id in (select tag_id from student_tag where student_id = #{id})")
    List<Tag> findStudentById();

}
