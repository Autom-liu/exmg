package com.edu.exmg.core.mapper;

import com.edu.exmg.common.service.Mapper;
import com.edu.exmg.core.bean.AnswerRecord;
import com.edu.exmg.core.bean.AnswerRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnswerRecordMapper extends Mapper<AnswerRecord> {
    long countByExample(AnswerRecordExample example);

    int deleteByExample(AnswerRecordExample example);

    List<AnswerRecord> selectByExample(AnswerRecordExample example);

    int updateByExampleSelective(@Param("record") AnswerRecord record, @Param("example") AnswerRecordExample example);

    int updateByExample(@Param("record") AnswerRecord record, @Param("example") AnswerRecordExample example);
}