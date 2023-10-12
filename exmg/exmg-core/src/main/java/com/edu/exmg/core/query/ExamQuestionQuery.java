package com.edu.exmg.core.query;

import com.edu.exmg.common.query.PageQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true)
public class ExamQuestionQuery extends PageQuery {

	private static final long serialVersionUID = 1L;

	private Integer examId;

	private String userId;

	private Integer recordId;

	private Boolean common;

	private Integer topCategory;

	private Integer status;

	private List<Integer> questionIds;


}
