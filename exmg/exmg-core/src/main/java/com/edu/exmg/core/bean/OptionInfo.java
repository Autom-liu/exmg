package com.edu.exmg.core.bean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "option_info")
public class OptionInfo {
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 选项
     */
    @Column(name = "`option`")
    private String option;

    /**
     * 关联题目id
     */
    @Column(name = "`question_id`")
    private Integer questionId;

    /**
     * 选项类型
     */
    @Column(name = "`option_type`")
    private Integer optionType;

    /**
     * 是否正确
     */
    @Column(name = "`right`")
    private Boolean right;

    /**
     * 选项解析
     */
    @Column(name = "`interpretation`")
    private String interpretation;

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
     * 获取选项
     *
     * @return option - 选项
     */
    public String getOption() {
        return option;
    }

    /**
     * 设置选项
     *
     * @param option 选项
     */
    public void setOption(String option) {
        this.option = option;
    }

    /**
     * 获取关联题目id
     *
     * @return question_id - 关联题目id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * 设置关联题目id
     *
     * @param questionId 关联题目id
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取选项类型
     *
     * @return option_type - 选项类型
     */
    public Integer getOptionType() {
        return optionType;
    }

    /**
     * 设置选项类型
     *
     * @param optionType 选项类型
     */
    public void setOptionType(Integer optionType) {
        this.optionType = optionType;
    }

    /**
     * 获取是否正确
     *
     * @return right - 是否正确
     */
    public Boolean getRight() {
        return right;
    }

    /**
     * 设置是否正确
     *
     * @param right 是否正确
     */
    public void setRight(Boolean right) {
        this.right = right;
    }

    /**
     * 获取选项解析
     *
     * @return interpretation - 选项解析
     */
    public String getInterpretation() {
        return interpretation;
    }

    /**
     * 设置选项解析
     *
     * @param interpretation 选项解析
     */
    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
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