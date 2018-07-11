package com.kaishengit.mapper;

import com.kaishengit.entity.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/10 0010
 */
public interface MovieMapper {

    List<Movie> findByKeys(@Param("title") String title);
    List<Movie> findByParam(Map<String, Object>queryMap);
    List<Movie> findById(@Param("idList") List<Integer> idList);


}
