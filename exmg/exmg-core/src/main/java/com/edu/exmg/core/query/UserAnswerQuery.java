package com.edu.exmg.core.query;

import com.edu.exmg.common.query.PageQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper=true)
public class UserAnswerQuery extends PageQuery {

	private static final long serialVersionUID = 1L;

}
