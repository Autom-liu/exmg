package com.edu.exmg.core.query;

import com.edu.exmg.common.query.PageQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true)
public class QuestionInfoQuery extends PageQuery {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String userId;

	private String question;

	private Integer questionType;

	private Integer topCategory;

	private Integer categoryId;

	private Boolean common;

	private Integer status;

	private Date beginDate;

	private Date endDate;

	public ExamQuestionQuery toExamQuestionQuery() {
		ExamQuestionQuery query = new ExamQuestionQuery();

		query.setUserId(userId);
		query.setCommon(common);
		query.setStatus(status);
		query.setTopCategory(topCategory);
		query.setCurrentPage(getCurrentPage());
		query.setPageSize(getPageSize());

		return query;
	}

}
