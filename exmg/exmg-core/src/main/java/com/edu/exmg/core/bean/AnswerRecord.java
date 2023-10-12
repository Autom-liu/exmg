package com.edu.exmg.core.bean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "answer_record")
public class AnswerRecord {
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
     * 总题数
     */
    @Column(name = "`total_num`")
    private Integer totalNum;

    /**
     * 正确题数
     */
    @Column(name = "`right_num`")
    private Integer rightNum;

    /**
     * 开始答题时间
     */
    @Column(name = "`start_time`")
    private Date startTime;

    /**
     * 结束答题时间
     */
    @Column(name = "`end_time`")
    private Date endTime;

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
     * 获取总题数
     *
     * @return total_num - 总题数
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * 设置总题数
     *
     * @param totalNum 总题数
     */
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 获取正确题数
     *
     * @return right_num - 正确题数
     */
    public Integer getRightNum() {
        return rightNum;
    }

    /**
     * 设置正确题数
     *
     * @param rightNum 正确题数
     */
    public void setRightNum(Integer rightNum) {
        this.rightNum = rightNum;
    }

    /**
     * 获取开始答题时间
     *
     * @return start_time - 开始答题时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始答题时间
     *
     * @param startTime 开始答题时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束答题时间
     *
     * @return end_time - 结束答题时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束答题时间
     *
     * @param endTime 结束答题时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}