package com.edu.exmg.core.vo;

import com.edu.exmg.core.bean.AnswerRecord;
import com.edu.exmg.core.bean.ExamInfo;
import lombok.Data;

import java.util.List;

@Data
public class AnswerRecordVO {

    private AnswerRecord record;

    private List<ExamQuestionVO> questionList;

    private ExamInfoVO examInfo;

}
