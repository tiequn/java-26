package com.kaishengit.movie;

import com.kaishengit.BaseTestCase;
import com.kaishengit.entity.Movie;
import com.kaishengit.mapper.MovieMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author guojiabang
 * @date 2018/7/19 0019
 */
public class MovieTestCase extends BaseTestCase {

    @Autowired
    private MovieMapper movieMapper;

    @Test
    public void testFindById(){
        Movie movie = movieMapper.selectByPrimaryKey(6);
        System.out.println(movie);
    }

    @Test
    public void testSave(){
        Movie movie = new Movie();
        movie.setTitle("天天向上");
        movie.setDirector("张老师");
        movie.setReleaseYear("2018");

        movieMapper.insert(movie);
        // 获得自动增长的id
        int id = movie.getId();
        System.out.println(id);
    }
}
