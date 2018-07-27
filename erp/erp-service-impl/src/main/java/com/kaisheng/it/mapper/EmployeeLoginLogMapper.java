package com.kaisheng.it.mapper;

import com.kaisheng.it.entity.EmployeeLoginLog;
import com.kaisheng.it.entity.EmployeeLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeLoginLogMapper {
    long countByExample(EmployeeLoginLogExample example);

    int deleteByExample(EmployeeLoginLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeLoginLog record);

    int insertSelective(EmployeeLoginLog record);

    List<EmployeeLoginLog> selectByExample(EmployeeLoginLogExample example);

    EmployeeLoginLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmployeeLoginLog record, @Param("example") EmployeeLoginLogExample example);

    int updateByExample(@Param("record") EmployeeLoginLog record, @Param("example") EmployeeLoginLogExample example);

    int updateByPrimaryKeySelective(EmployeeLoginLog record);

    int updateByPrimaryKey(EmployeeLoginLog record);
}