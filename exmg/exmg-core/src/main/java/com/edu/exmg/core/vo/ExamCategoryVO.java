package com.edu.exmg.core.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ExamCategoryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String categoryName;
    
    private Integer parentId;
    
    private Integer level;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Boolean status;
    
    private Integer version;

    public static List<ExamCategoryVO> resultList() {
        List<ExamCategoryVO> result = new ArrayList<>();
        Date now = new Date();
        result.add(new ExamCategoryVO(1, "计算机基础知识", 0, 1, now, now, false, 0));
        result.add(new ExamCategoryVO(101, "数据结构与算法", 1, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(102, "计算机体系结构", 1, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(103, "操作系统", 1, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(104, "计算机网络", 1, 2, now, now, false, 0));

        result.add(new ExamCategoryVO(2, "编程语言基础", 0, 1, now, now, false, 0));
        result.add(new ExamCategoryVO(201, "java语言基础", 2, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(202, "设计模式", 2, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(203, "java语言核心技术(JVM与多线程)", 2, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(204, "Linux Shell脚本", 2, 2, now, now, false, 0));

        result.add(new ExamCategoryVO(3, "框架与中间件技术", 0, 1, now, now, false, 0));
        result.add(new ExamCategoryVO(301, "Spring及Springboot", 3, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(302, "mybatis", 3, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(303, "数据库", 3, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(304, "分布式缓存中间件", 3, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(305, "分布式消息中间件", 3, 2, now, now, false, 0));
        result.add(new ExamCategoryVO(306, "分布式负载均衡", 3, 2, now, now, false, 0));


        result.add(new ExamCategoryVO(4, "大数据建模与分析", 0, 1, now, now, false, 0));
        result.add(new ExamCategoryVO(401, "大数据分析", 4, 2, now, now, false, 0));
        return result;
    }
    
}
