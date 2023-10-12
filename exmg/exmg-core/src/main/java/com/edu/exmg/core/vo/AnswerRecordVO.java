package com.edu.exmg.core.vo;

import com.edu.exmg.core.bean.AnswerRecord;
import lombok.Data;

import java.util.List;

@Data
public class AnswerRecordVO {

    private AnswerRecord record;

    private List<ExamQuestionVO> questionList;

}
