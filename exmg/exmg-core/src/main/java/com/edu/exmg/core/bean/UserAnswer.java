package com.edu.exmg.core.bean;

import javax.persistence.*;

@Table(name = "user_answer")
public class UserAnswer {
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户
     */
    @Column(name = "`user_id`")
    private String userId;

    /**
     * 试题
     */
    @Column(name = "`exam_id`")
    private Integer examId;

    /**
     * 题目
     */
    @Column(name = "`question_id`")
    private Integer questionId;

    /**
     * 选项
     */
    @Column(name = "`option_id`")
    private Integer optionId;

    /**
     * 实际得分
     */
    @Column(name = "`score`")
    private Integer score;

    /**
     * 排序号
     */
    @Column(name = "`sorted`")
    private Integer sorted;

    /**
     * 答案
     */
    @Column(name = "`answer`")
    private String answer;

    /**
     * 正确
     */
    @Column(name = "`right`")
    private Boolean right;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户
     *
     * @return user_id - 用户
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户
     *
     * @param userId 用户
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取试题
     *
     * @return exam_id - 试题
     */
    public Integer getExamId() {
        return examId;
    }

    /**
     * 设置试题
     *
     * @param examId 试题
     */
    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    /**
     * 获取题目
     *
     * @return question_id - 题目
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * 设置题目
     *
     * @param questionId 题目
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取选项
     *
     * @return option_id - 选项
     */
    public Integer getOptionId() {
        return optionId;
    }

    /**
     * 设置选项
     *
     * @param optionId 选项
     */
    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    /**
     * 获取实际得分
     *
     * @return score - 实际得分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置实际得分
     *
     * @param score 实际得分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取排序号
     *
     * @return sorted - 排序号
     */
    public Integer getSorted() {
        return sorted;
    }

    /**
     * 设置排序号
     *
     * @param sorted 排序号
     */
    public void setSorted(Integer sorted) {
        this.sorted = sorted;
    }

    /**
     * 获取答案
     *
     * @return answer - 答案
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置答案
     *
     * @param answer 答案
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 获取正确
     *
     * @return right - 正确
     */
    public Boolean getRight() {
        return right;
    }

    /**
     * 设置正确
     *
     * @param right 正确
     */
    public void setRight(Boolean right) {
        this.right = right;
    }
}