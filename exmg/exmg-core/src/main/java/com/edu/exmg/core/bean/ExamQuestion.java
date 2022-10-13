package com.edu.exmg.core.bean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "exam_question")
public class ExamQuestion {
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 试题编号
     */
    @Column(name = "`exam_id`")
    private Integer examId;

    /**
     * 题目编号
     */
    @Column(name = "`question_id`")
    private Integer questionId;

    /**
     * 分值
     */
    @Column(name = "`score`")
    private Integer score;

    /**
     * 序号
     */
    @Column(name = "`sorted`")
    private Integer sorted;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 状态 0-正常
     */
    @Column(name = "`status`")
    private Boolean status;

    /**
     * 版本号
     */
    @Column(name = "`version`")
    private Integer version;

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
     * 获取试题编号
     *
     * @return exam_id - 试题编号
     */
    public Integer getExamId() {
        return examId;
    }

    /**
     * 设置试题编号
     *
     * @param examId 试题编号
     */
    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    /**
     * 获取题目编号
     *
     * @return question_id - 题目编号
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * 设置题目编号
     *
     * @param questionId 题目编号
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取分值
     *
     * @return score - 分值
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置分值
     *
     * @param score 分值
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取序号
     *
     * @return sorted - 序号
     */
    public Integer getSorted() {
        return sorted;
    }

    /**
     * 设置序号
     *
     * @param sorted 序号
     */
    public void setSorted(Integer sorted) {
        this.sorted = sorted;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取状态 0-正常
     *
     * @return status - 状态 0-正常
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态 0-正常
     *
     * @param status 状态 0-正常
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}