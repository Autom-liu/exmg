package com.edu.exmg.core.mapper;

import com.edu.exmg.common.service.Mapper;
import com.edu.exmg.core.bean.UserAnswer;
import com.edu.exmg.core.bean.UserAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAnswerMapper extends Mapper<UserAnswer> {
    long countByExample(UserAnswerExample example);

    int deleteByExample(UserAnswerExample example);

    List<UserAnswer> selectByExample(UserAnswerExample example);

    int updateByExampleSelective(@Param("record") UserAnswer record, @Param("example") UserAnswerExample example);

    int updateByExample(@Param("record") UserAnswer record, @Param("example") UserAnswerExample example);
}