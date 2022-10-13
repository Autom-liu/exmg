package com.edu.exmg.admin.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.edu.exmg.core.vo.ExamCategoryVO;
import lombok.Data;


import java.util.Date;
import java.util.List;

/**
 * @author autom
 * @date 2022-01-10
 **/
@Data
public class ExportCategory {

    @ExcelProperty(value = "分类编号",order = 0)
    private Integer id;

    @ExcelProperty(value = "分类名称",order = 1)
    private String categoryName;

    @ExcelProperty(value = "上级分类编号",order = 2)
    private Integer parentId;

    @ExcelProperty(value = "分类层级",order = 2)
    private Integer level;



}
