package com.edu.exmg.core.bean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "question_info")
public class QuestionInfo {
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 出题人用户id
     */
    @Column(name = "`user_id`")
    private String userId;

    /**
     * 昵称
     */
    @Column(name = "`nick_name`")
    private String nickName;

    /**
     * 题目
     */
    @Column(name = "`question`")
    private String question;

    /**
     * 题目类型
     */
    @Column(name = "`question_type`")
    private Integer questionType;

    /**
     * 难度
     */
    @Column(name = "`difficulty`")
    private Integer difficulty;

    /**
     * 顶级分类id
     */
    @Column(name = "`top_category`")
    private Integer topCategory;

    /**
     * 分类id
     */
    @Column(name = "`category_id`")
    private Integer categoryId;

    /**
     * 答案解析
     */
    @Column(name = "`interpretation`")
    private String interpretation;

    /**
     * 图片地址
     */
    @Column(name = "`pics_url`")
    private String picsUrl;

    /**
     * 是否贡献为公共题目
     */
    @Column(name = "`common`")
    private Boolean common;

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
    private Byte status;

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
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取题目
     *
     * @return question - 题目
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 设置题目
     *
     * @param question 题目
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 获取题目类型
     *
     * @return question_type - 题目类型
     */
    public Integer getQuestionType() {
        return questionType;
    }

    /**
     * 设置题目类型
     *
     * @param questionType 题目类型
     */
    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    /**
     * 获取难度
     *
     * @return difficulty - 难度
     */
    public Integer getDifficulty() {
        return difficulty;
    }

    /**
     * 设置难度
     *
     * @param difficulty 难度
     */
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * 获取顶级分类id
     *
     * @return top_category - 顶级分类id
     */
    public Integer getTopCategory() {
        return topCategory;
    }

    /**
     * 设置顶级分类id
     *
     * @param topCategory 顶级分类id
     */
    public void setTopCategory(Integer topCategory) {
        this.topCategory = topCategory;
    }

    /**
     * 获取分类id
     *
     * @return category_id - 分类id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类id
     *
     * @param categoryId 分类id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取答案解析
     *
     * @return interpretation - 答案解析
     */
    public String getInterpretation() {
        return interpretation;
    }

    /**
     * 设置答案解析
     *
     * @param interpretation 答案解析
     */
    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    /**
     * 获取图片地址
     *
     * @return pics_url - 图片地址
     */
    public String getPicsUrl() {
        return picsUrl;
    }

    /**
     * 设置图片地址
     *
     * @param picsUrl 图片地址
     */
    public void setPicsUrl(String picsUrl) {
        this.picsUrl = picsUrl;
    }

    /**
     * 获取是否贡献为公共题目
     *
     * @return common - 是否贡献为公共题目
     */
    public Boolean getCommon() {
        return common;
    }

    /**
     * 设置是否贡献为公共题目
     *
     * @param common 是否贡献为公共题目
     */
    public void setCommon(Boolean common) {
        this.common = common;
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
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态 0-正常
     *
     * @param status 状态 0-正常
     */
    public void setStatus(Byte status) {
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