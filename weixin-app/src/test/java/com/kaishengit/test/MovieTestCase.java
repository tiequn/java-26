package com.kaishengit.test;

import com.kaishengit.entity.Movie;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author guojiabang
 * @date 2018/7/10 0010
 */
public class MovieTestCase {

    Logger logger = LoggerFactory.getLogger(MovieTestCase.class);

    private SqlSession sqlSession;
    private MovieMapper movieMapper;

    @Before
    public void before() {
        sqlSession = MybatisUtils.getSqlSession();
        // 动态代理：sqlSession对象根据接口的class动态创建接口的实现类
        movieMapper = sqlSession.getMapper(MovieMapper.class);// 获得接口的实现类对象
    }

    @After
    public void after() {
        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testFindByKeys(){
        String title = "%大话西游%";
        List<Movie> movieList = movieMapper.findByKeys(title);

        logger.debug("size:{}",movieList.size());
    }

    @Test
    public void testFindByParam(){
        Map<String, Object> maps = new HashMap<>();
        maps.put("title", "%大话西游%");
        maps.put("director", "%周星星%");

        List<Movie> movieList = movieMapper.findByParam(maps);
        logger.debug("size:{}",movieList.size());
    }

    @Test
    public  void testFindById(){

        List<Integer> idList = Arrays.asList(4,5,6);
        List<Movie> movieList = movieMapper.findById(idList);
        logger.debug("size:{}", movieList.size());

    }

    @Test
    public void testInsertMovie(){
        Movie movie = new Movie();
        movie.setTitle("脱身");
        movie.setDirector("陈坤");
        movie.setReleaseYear("2018");

        int count = movieMapper.insertMovie(movie);
        logger.debug("count:{}",count);
        sqlSession.commit();

    }

    @Test
    public void testFindMovieById(){
        Movie movie = movieMapper.selectMovie(8);
        logger.debug("movie:{}",movie);
    }


}
