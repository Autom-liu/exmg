package com.edu.exmg.gateway.dto;

import com.edu.exmg.core.dto.UserAnswerDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserAnswerSubmitDTO {

    private Integer examId;

    private String userId;

    private Integer recordId;

    private List<UserAnswerDTO> answerList;

}
