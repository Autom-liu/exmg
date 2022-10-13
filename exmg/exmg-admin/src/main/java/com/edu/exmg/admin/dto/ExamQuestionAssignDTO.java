package com.edu.exmg.admin.dto;

import com.edu.exmg.core.dto.ExamQuestionDTO;
import lombok.Data;

import java.util.List;

@Data
public class ExamQuestionAssignDTO {

    private Integer examId;

    List<ExamQuestionDTO> exqtList;


}
