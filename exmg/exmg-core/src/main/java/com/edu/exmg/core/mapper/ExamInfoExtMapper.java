package com.edu.exmg.core.mapper;

import com.edu.exmg.core.bean.ExamInfo;
import com.edu.exmg.core.query.ExamInfoQuery;

import java.util.List;

public interface ExamInfoExtMapper {

    List<ExamInfo> selectUserExamList(ExamInfoQuery query);

}
