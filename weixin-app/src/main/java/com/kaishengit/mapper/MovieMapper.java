package com.kaishengit.mapper;

import com.kaishengit.entity.Movie;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/10 0010
 */
public interface MovieMapper {
    /**
     * List模糊查询
     * @param title
     * @return List<Movie>
     */
    List<Movie> findByKeys(@Param("title") String title);

    /**
     * Map模糊查询
     * @param queryMap
     * @return List<Movie>
     */
    List<Movie> findByParam(Map<String, Object>queryMap);

    /**
     * 根据id使用foreach批量查询
     * @param idList
     * @return List<Movie>
     */
    List<Movie> findById(@Param("idList") List<Integer> idList);


    /**
     *
     *使用注解的方式
     */
    @Insert("insert into movie(title, director, release_year) " +
            "values(#{title}, #{director}, #{releaseYear})")
    int insertMovie(Movie movie);

    @Select("select title, director, release_year from movie where id = #{id}")
    Movie selectMovie(Integer id);

	

}
