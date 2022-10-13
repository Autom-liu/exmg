package com.edu.exmg.core.query;

import com.edu.exmg.common.query.PageQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true)
public class ExamInfoQuery extends PageQuery {

	private static final long serialVersionUID = 1L;

	private Integer userFrom;

	private String userId;

	private String examName;

	private Integer answerMode;

	private Boolean banner;

	private Boolean autoGenerate;

	private Boolean status;

	private Date queryDate;

}
