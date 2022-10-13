package com.edu.exmg.core.bean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "exmg_wx")
public class Wxuser {
    /**
     * 微信用户id
     */
    @Id
    @Column(name = "`id`")
    private Integer id;

    /**
     * 用户来源
     */
    @Column(name = "`user_from`")
    private Integer userFrom;

    /**
     * 用户openid
     */
    @Column(name = "`wx_openid`")
    private String wxOpenid;

    /**
     * 昵称
     */
    @Column(name = "`nick_name`")
    private String nickName;

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
     * 获取微信用户id
     *
     * @return id - 微信用户id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置微信用户id
     *
     * @param id 微信用户id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户来源
     *
     * @return user_from - 用户来源
     */
    public Integer getUserFrom() {
        return userFrom;
    }

    /**
     * 设置用户来源
     *
     * @param userFrom 用户来源
     */
    public void setUserFrom(Integer userFrom) {
        this.userFrom = userFrom;
    }

    /**
     * 获取用户openid
     *
     * @return wx_openid - 用户openid
     */
    public String getWxOpenid() {
        return wxOpenid;
    }

    /**
     * 设置用户openid
     *
     * @param wxOpenid 用户openid
     */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
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