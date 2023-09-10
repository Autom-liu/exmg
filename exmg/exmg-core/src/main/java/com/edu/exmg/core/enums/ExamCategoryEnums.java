package com.edu.exmg.core.enums;

import com.edu.exmg.common.base.BaseIntegerBizEnum;
import com.edu.exmg.common.util.EnumUtils;
import com.edu.exmg.core.vo.ExamCategoryVO;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExamCategoryEnums implements BaseIntegerBizEnum {


    CSBASIC(1, "计算机基础知识", 0, true),

    DATA_STRUCT(101, "数据结构与算法", 1),

    CS_STRUCT(102, "计算机体系结构", 1),

    OS_SYSTEM(103, "操作系统", 1),

    CS_NETWORK(104, "计算机网络", 1),
    
    PROGRAMER(2, "编程语言基础", 0, true),

    JAVA(201, "java语言基础", 2),

    DESIGN_MODE(20101, "设计模式", 2),

    JAVA_BASE(20102, "java语言核心技术(JVM与多线程)", 2),

    SPRING(20103, "Spring及Springboot", 2),

    MYBATIS(20104, "mybatis", 2),

    LINUX_SHELL(204, "Linux Shell脚本", 2),
    
    MIDWARE_DATABASE(3, "数据库及中间件", 0, true),

    DATABASE(303, "数据库", 3),

    MYSQL(30302, "mysql数据库", 3),

    ORACLE(30303, "oracle数据库", 3),

    MEMORY_STORE(301, "分布式缓存中间件", 3),

    MESSAGE_STORE(302, "分布式消息中间件", 3),

    BIGDATA(4, "大数据技术", 0, true),

    SURVEY(9, "问卷调查", 0, true),

    ;

    private Integer code;
    
    private String msg;

    private Integer parentId;

    private boolean top;

    ExamCategoryEnums(Integer code, String msg, Integer parentId) {
        this(code, msg, parentId, false);
    }

    ExamCategoryEnums(Integer code, String msg, Integer parentId, boolean top) {
        this.code = code;
        this.msg = msg;
        this.parentId = parentId;
        this.top = top;
    }
    
    

    @Override
    public Integer getCode() {
        return code;
    }

    public Integer getParentId() {
        return parentId;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public boolean isTop() {
        return top;
    }
}
