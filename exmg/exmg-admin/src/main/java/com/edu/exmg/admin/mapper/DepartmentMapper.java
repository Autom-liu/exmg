package com.edu.exmg.admin.mapper;


import com.edu.exmg.admin.bean.Department;
import com.edu.exmg.admin.bean.DepartmentExample;
import com.edu.exmg.common.service.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper extends Mapper<Department> {
    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    List<Department> selectByExample(DepartmentExample example);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);
}