package com.edu.exmg.core.bean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "exam_info")
public class ExamInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC", strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;

    /**
     * 试题名称
     */
    @Column(name = "`exam_name`")
    private String examName;

    /**
     * 试题说明
     */
    @Column(name = "`exam_remark`")
    private String examRemark;

    /**
     * 出题人来源
     */
    @Column(name = "`user_from`")
    private Integer userFrom;

    /**
     * 出题人用户id
     */
    @Column(name = "`user_id`")
    private String userId;

    /**
     * 试题总分
     */
    @Column(name = "`total_score`")
    private Integer totalScore;

    /**
     * 答题模式
     */
    @Column(name = "`answer_mode`")
    private Integer answerMode;

    /**
     * 最大答题次数
     */
    @Column(name = "`max_count`")
    private Integer maxCount;

    /**
     * 缩略图地址
     */
    @Column(name = "`pic_url`")
    private String picUrl;

    /**
     * 是否放首页
     */
    @Column(name = "`banner`")
    private Boolean banner;

    /**
     * 是否随即生成的
     */
    @Column(name = "`auto_generate`")
    private Boolean autoGenerate;

    /**
     * 答题开始时间
     */
    @Column(name = "`beginTime`")
    private Date begintime;

    /**
     * 答题结束时间
     */
    @Column(name = "`endTime`")
    private Date endtime;

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
     * 获取试题名称
     *
     * @return exam_name - 试题名称
     */
    public String getExamName() {
        return examName;
    }

    /**
     * 设置试题名称
     *
     * @param examName 试题名称
     */
    public void setExamName(String examName) {
        this.examName = examName;
    }

    /**
     * 获取试题说明
     *
     * @return exam_remark - 试题说明
     */
    public String getExamRemark() {
        return examRemark;
    }

    /**
     * 设置试题说明
     *
     * @param examRemark 试题说明
     */
    public void setExamRemark(String examRemark) {
        this.examRemark = examRemark;
    }

    /**
     * 获取出题人来源
     *
     * @return user_from - 出题人来源
     */
    public Integer getUserFrom() {
        return userFrom;
    }

    /**
     * 设置出题人来源
     *
     * @param userFrom 出题人来源
     */
    public void setUserFrom(Integer userFrom) {
        this.userFrom = userFrom;
    }

    /**
     * 获取出题人用户id
     *
     * @return user_id - 出题人用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置出题人用户id
     *
     * @param userId 出题人用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取试题总分
     *
     * @return total_score - 试题总分
     */
    public Integer getTotalScore() {
        return totalScore;
    }

    /**
     * 设置试题总分
     *
     * @param totalScore 试题总分
     */
    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * 获取答题模式
     *
     * @return answer_mode - 答题模式
     */
    public Integer getAnswerMode() {
        return answerMode;
    }

    /**
     * 设置答题模式
     *
     * @param answerMode 答题模式
     */
    public void setAnswerMode(Integer answerMode) {
        this.answerMode = answerMode;
    }

    /**
     * 获取最大答题次数
     *
     * @return max_count - 最大答题次数
     */
    public Integer getMaxCount() {
        return maxCount;
    }

    /**
     * 设置最大答题次数
     *
     * @param maxCount 最大答题次数
     */
    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * 获取缩略图地址
     *
     * @return pic_url - 缩略图地址
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置缩略图地址
     *
     * @param picUrl 缩略图地址
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取是否放首页
     *
     * @return banner - 是否放首页
     */
    public Boolean getBanner() {
        return banner;
    }

    /**
     * 设置是否放首页
     *
     * @param banner 是否放首页
     */
    public void setBanner(Boolean banner) {
        this.banner = banner;
    }

    /**
     * 获取是否随即生成的
     *
     * @return auto_generate - 是否随即生成的
     */
    public Boolean getAutoGenerate() {
        return autoGenerate;
    }

    /**
     * 设置是否随即生成的
     *
     * @param autoGenerate 是否随即生成的
     */
    public void setAutoGenerate(Boolean autoGenerate) {
        this.autoGenerate = autoGenerate;
    }

    /**
     * 获取答题开始时间
     *
     * @return beginTime - 答题开始时间
     */
    public Date getBegintime() {
        return begintime;
    }

    /**
     * 设置答题开始时间
     *
     * @param begintime 答题开始时间
     */
    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    /**
     * 获取答题结束时间
     *
     * @return endTime - 答题结束时间
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * 设置答题结束时间
     *
     * @param endtime 答题结束时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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