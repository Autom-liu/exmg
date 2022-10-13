package com.edu.exmg.core.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edu.exmg.common.base.BaseExample;

public class ExamInfoExample implements BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                return;
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                return;
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                return;
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andExamNameIsNull() {
            addCriterion("`exam_name` is null");
            return (Criteria) this;
        }

        public Criteria andExamNameIsNotNull() {
            addCriterion("`exam_name` is not null");
            return (Criteria) this;
        }

        public Criteria andExamNameEqualTo(String value) {
            addCriterion("`exam_name` =", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotEqualTo(String value) {
            addCriterion("`exam_name` <>", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameGreaterThan(String value) {
            addCriterion("`exam_name` >", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameGreaterThanOrEqualTo(String value) {
            addCriterion("`exam_name` >=", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLessThan(String value) {
            addCriterion("`exam_name` <", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLessThanOrEqualTo(String value) {
            addCriterion("`exam_name` <=", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLike(String value) {
            addCriterion("`exam_name` like", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotLike(String value) {
            addCriterion("`exam_name` not like", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameIn(List<String> values) {
            addCriterion("`exam_name` in", values, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotIn(List<String> values) {
            addCriterion("`exam_name` not in", values, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameBetween(String value1, String value2) {
            addCriterion("`exam_name` between", value1, value2, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotBetween(String value1, String value2) {
            addCriterion("`exam_name` not between", value1, value2, "examName");
            return (Criteria) this;
        }

        public Criteria andExamRemarkIsNull() {
            addCriterion("`exam_remark` is null");
            return (Criteria) this;
        }

        public Criteria andExamRemarkIsNotNull() {
            addCriterion("`exam_remark` is not null");
            return (Criteria) this;
        }

        public Criteria andExamRemarkEqualTo(String value) {
            addCriterion("`exam_remark` =", value, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkNotEqualTo(String value) {
            addCriterion("`exam_remark` <>", value, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkGreaterThan(String value) {
            addCriterion("`exam_remark` >", value, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("`exam_remark` >=", value, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkLessThan(String value) {
            addCriterion("`exam_remark` <", value, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkLessThanOrEqualTo(String value) {
            addCriterion("`exam_remark` <=", value, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkLike(String value) {
            addCriterion("`exam_remark` like", value, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkNotLike(String value) {
            addCriterion("`exam_remark` not like", value, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkIn(List<String> values) {
            addCriterion("`exam_remark` in", values, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkNotIn(List<String> values) {
            addCriterion("`exam_remark` not in", values, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkBetween(String value1, String value2) {
            addCriterion("`exam_remark` between", value1, value2, "examRemark");
            return (Criteria) this;
        }

        public Criteria andExamRemarkNotBetween(String value1, String value2) {
            addCriterion("`exam_remark` not between", value1, value2, "examRemark");
            return (Criteria) this;
        }

        public Criteria andUserFromIsNull() {
            addCriterion("`user_from` is null");
            return (Criteria) this;
        }

        public Criteria andUserFromIsNotNull() {
            addCriterion("`user_from` is not null");
            return (Criteria) this;
        }

        public Criteria andUserFromEqualTo(Integer value) {
            addCriterion("`user_from` =", value, "userFrom");
            return (Criteria) this;
        }

        public Criteria andUserFromNotEqualTo(Integer value) {
            addCriterion("`user_from` <>", value, "userFrom");
            return (Criteria) this;
        }

        public Criteria andUserFromGreaterThan(Integer value) {
            addCriterion("`user_from` >", value, "userFrom");
            return (Criteria) this;
        }

        public Criteria andUserFromGreaterThanOrEqualTo(Integer value) {
            addCriterion("`user_from` >=", value, "userFrom");
            return (Criteria) this;
        }

        public Criteria andUserFromLessThan(Integer value) {
            addCriterion("`user_from` <", value, "userFrom");
            return (Criteria) this;
        }

        public Criteria andUserFromLessThanOrEqualTo(Integer value) {
            addCriterion("`user_from` <=", value, "userFrom");
            return (Criteria) this;
        }

        public Criteria andUserFromIn(List<Integer> values) {
            addCriterion("`user_from` in", values, "userFrom");
            return (Criteria) this;
        }

        public Criteria andUserFromNotIn(List<Integer> values) {
            addCriterion("`user_from` not in", values, "userFrom");
            return (Criteria) this;
        }

        public Criteria andUserFromBetween(Integer value1, Integer value2) {
            addCriterion("`user_from` between", value1, value2, "userFrom");
            return (Criteria) this;
        }

        public Criteria andUserFromNotBetween(Integer value1, Integer value2) {
            addCriterion("`user_from` not between", value1, value2, "userFrom");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("`user_id` is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("`user_id` is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("`user_id` =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("`user_id` <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("`user_id` >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("`user_id` >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("`user_id` <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("`user_id` <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("`user_id` like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("`user_id` not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("`user_id` in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("`user_id` not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("`user_id` between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("`user_id` not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNull() {
            addCriterion("`total_score` is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNotNull() {
            addCriterion("`total_score` is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreEqualTo(Integer value) {
            addCriterion("`total_score` =", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotEqualTo(Integer value) {
            addCriterion("`total_score` <>", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThan(Integer value) {
            addCriterion("`total_score` >", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("`total_score` >=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThan(Integer value) {
            addCriterion("`total_score` <", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThanOrEqualTo(Integer value) {
            addCriterion("`total_score` <=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIn(List<Integer> values) {
            addCriterion("`total_score` in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotIn(List<Integer> values) {
            addCriterion("`total_score` not in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreBetween(Integer value1, Integer value2) {
            addCriterion("`total_score` between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("`total_score` not between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andAnswerModeIsNull() {
            addCriterion("`answer_mode` is null");
            return (Criteria) this;
        }

        public Criteria andAnswerModeIsNotNull() {
            addCriterion("`answer_mode` is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerModeEqualTo(Integer value) {
            addCriterion("`answer_mode` =", value, "answerMode");
            return (Criteria) this;
        }

        public Criteria andAnswerModeNotEqualTo(Integer value) {
            addCriterion("`answer_mode` <>", value, "answerMode");
            return (Criteria) this;
        }

        public Criteria andAnswerModeGreaterThan(Integer value) {
            addCriterion("`answer_mode` >", value, "answerMode");
            return (Criteria) this;
        }

        public Criteria andAnswerModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`answer_mode` >=", value, "answerMode");
            return (Criteria) this;
        }

        public Criteria andAnswerModeLessThan(Integer value) {
            addCriterion("`answer_mode` <", value, "answerMode");
            return (Criteria) this;
        }

        public Criteria andAnswerModeLessThanOrEqualTo(Integer value) {
            addCriterion("`answer_mode` <=", value, "answerMode");
            return (Criteria) this;
        }

        public Criteria andAnswerModeIn(List<Integer> values) {
            addCriterion("`answer_mode` in", values, "answerMode");
            return (Criteria) this;
        }

        public Criteria andAnswerModeNotIn(List<Integer> values) {
            addCriterion("`answer_mode` not in", values, "answerMode");
            return (Criteria) this;
        }

        public Criteria andAnswerModeBetween(Integer value1, Integer value2) {
            addCriterion("`answer_mode` between", value1, value2, "answerMode");
            return (Criteria) this;
        }

        public Criteria andAnswerModeNotBetween(Integer value1, Integer value2) {
            addCriterion("`answer_mode` not between", value1, value2, "answerMode");
            return (Criteria) this;
        }

        public Criteria andMaxCountIsNull() {
            addCriterion("`max_count` is null");
            return (Criteria) this;
        }

        public Criteria andMaxCountIsNotNull() {
            addCriterion("`max_count` is not null");
            return (Criteria) this;
        }

        public Criteria andMaxCountEqualTo(Integer value) {
            addCriterion("`max_count` =", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotEqualTo(Integer value) {
            addCriterion("`max_count` <>", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountGreaterThan(Integer value) {
            addCriterion("`max_count` >", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("`max_count` >=", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountLessThan(Integer value) {
            addCriterion("`max_count` <", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountLessThanOrEqualTo(Integer value) {
            addCriterion("`max_count` <=", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountIn(List<Integer> values) {
            addCriterion("`max_count` in", values, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotIn(List<Integer> values) {
            addCriterion("`max_count` not in", values, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountBetween(Integer value1, Integer value2) {
            addCriterion("`max_count` between", value1, value2, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotBetween(Integer value1, Integer value2) {
            addCriterion("`max_count` not between", value1, value2, "maxCount");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNull() {
            addCriterion("`pic_url` is null");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNotNull() {
            addCriterion("`pic_url` is not null");
            return (Criteria) this;
        }

        public Criteria andPicUrlEqualTo(String value) {
            addCriterion("`pic_url` =", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotEqualTo(String value) {
            addCriterion("`pic_url` <>", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThan(String value) {
            addCriterion("`pic_url` >", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("`pic_url` >=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThan(String value) {
            addCriterion("`pic_url` <", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThanOrEqualTo(String value) {
            addCriterion("`pic_url` <=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLike(String value) {
            addCriterion("`pic_url` like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotLike(String value) {
            addCriterion("`pic_url` not like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlIn(List<String> values) {
            addCriterion("`pic_url` in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotIn(List<String> values) {
            addCriterion("`pic_url` not in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlBetween(String value1, String value2) {
            addCriterion("`pic_url` between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotBetween(String value1, String value2) {
            addCriterion("`pic_url` not between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andBannerIsNull() {
            addCriterion("`banner` is null");
            return (Criteria) this;
        }

        public Criteria andBannerIsNotNull() {
            addCriterion("`banner` is not null");
            return (Criteria) this;
        }

        public Criteria andBannerEqualTo(Boolean value) {
            addCriterion("`banner` =", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerNotEqualTo(Boolean value) {
            addCriterion("`banner` <>", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerGreaterThan(Boolean value) {
            addCriterion("`banner` >", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`banner` >=", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerLessThan(Boolean value) {
            addCriterion("`banner` <", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerLessThanOrEqualTo(Boolean value) {
            addCriterion("`banner` <=", value, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerIn(List<Boolean> values) {
            addCriterion("`banner` in", values, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerNotIn(List<Boolean> values) {
            addCriterion("`banner` not in", values, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerBetween(Boolean value1, Boolean value2) {
            addCriterion("`banner` between", value1, value2, "banner");
            return (Criteria) this;
        }

        public Criteria andBannerNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`banner` not between", value1, value2, "banner");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateIsNull() {
            addCriterion("`auto_generate` is null");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateIsNotNull() {
            addCriterion("`auto_generate` is not null");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateEqualTo(Boolean value) {
            addCriterion("`auto_generate` =", value, "autoGenerate");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateNotEqualTo(Boolean value) {
            addCriterion("`auto_generate` <>", value, "autoGenerate");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateGreaterThan(Boolean value) {
            addCriterion("`auto_generate` >", value, "autoGenerate");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`auto_generate` >=", value, "autoGenerate");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateLessThan(Boolean value) {
            addCriterion("`auto_generate` <", value, "autoGenerate");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateLessThanOrEqualTo(Boolean value) {
            addCriterion("`auto_generate` <=", value, "autoGenerate");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateIn(List<Boolean> values) {
            addCriterion("`auto_generate` in", values, "autoGenerate");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateNotIn(List<Boolean> values) {
            addCriterion("`auto_generate` not in", values, "autoGenerate");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateBetween(Boolean value1, Boolean value2) {
            addCriterion("`auto_generate` between", value1, value2, "autoGenerate");
            return (Criteria) this;
        }

        public Criteria andAutoGenerateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`auto_generate` not between", value1, value2, "autoGenerate");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNull() {
            addCriterion("`beginTime` is null");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNotNull() {
            addCriterion("`beginTime` is not null");
            return (Criteria) this;
        }

        public Criteria andBegintimeEqualTo(Date value) {
            addCriterion("`beginTime` =", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotEqualTo(Date value) {
            addCriterion("`beginTime` <>", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThan(Date value) {
            addCriterion("`beginTime` >", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`beginTime` >=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThan(Date value) {
            addCriterion("`beginTime` <", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThanOrEqualTo(Date value) {
            addCriterion("`beginTime` <=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeIn(List<Date> values) {
            addCriterion("`beginTime` in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotIn(List<Date> values) {
            addCriterion("`beginTime` not in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeBetween(Date value1, Date value2) {
            addCriterion("`beginTime` between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotBetween(Date value1, Date value2) {
            addCriterion("`beginTime` not between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("`endTime` is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("`endTime` is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("`endTime` =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("`endTime` <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("`endTime` >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`endTime` >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("`endTime` <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("`endTime` <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("`endTime` in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("`endTime` not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("`endTime` between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("`endTime` not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("`create_time` is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("`create_time` is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("`create_time` =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("`create_time` <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("`create_time` >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`create_time` >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("`create_time` <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("`create_time` <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("`create_time` in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("`create_time` not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("`create_time` between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("`create_time` not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("`update_time` is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("`update_time` is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("`update_time` =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("`update_time` <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("`update_time` >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("`update_time` >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("`update_time` <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("`update_time` <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("`update_time` in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("`update_time` not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("`update_time` between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("`update_time` not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("`version` is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("`version` is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("`version` =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("`version` <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("`version` >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("`version` >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("`version` <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("`version` <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("`version` in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("`version` not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("`version` between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("`version` not between", value1, value2, "version");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
