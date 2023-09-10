package com.edu.exmg.admin.api;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.edu.exmg.admin.bean.User;
import com.edu.exmg.admin.config.CurrentUser;
import com.edu.exmg.admin.enums.ErrorEnum;
import com.edu.exmg.admin.vo.ExportCategory;
import com.edu.exmg.admin.vo.ImportQuestion;
import com.edu.exmg.admin.vo.UserEntity;
import com.edu.exmg.common.exception.BizException;
import com.edu.exmg.common.util.CollectionUtils;
import com.edu.exmg.common.util.StringUtils;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.common.vo.Result;
import com.edu.exmg.common.vo.ResultList;
import com.edu.exmg.common.vo.ResultPage;
import com.edu.exmg.core.dto.ExamQuestionDTO;
import com.edu.exmg.core.dto.OptionInfoDTO;
import com.edu.exmg.core.dto.QuestionInfoDTO;
import com.edu.exmg.core.enums.ExamCategoryEnums;
import com.edu.exmg.core.query.ExamQuestionQuery;
import com.edu.exmg.core.query.QuestionInfoQuery;
import com.edu.exmg.core.service.ExamQuestionService;
import com.edu.exmg.core.service.QuestionInfoService;
import com.edu.exmg.core.vo.ExamQuestionVO;
import com.edu.exmg.core.vo.QuestionInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author autom
 * @date 2022-01-05
 **/
@Slf4j
@RestController
@RequestMapping("admin")
public class QuestionInfoAPI {

    @Autowired
    private QuestionInfoService questionInfoService;

    @Autowired
    private ExamQuestionService examQuestionService;

    @PostMapping("question/create")
    public IResult create(@Valid @RequestBody QuestionInfoDTO questionInfoDTO, @CurrentUser UserEntity userEntity) throws BizException {
        User user = userEntity.getUser();
        String userId = user.getUserId();
        String userName = user.getUserName();
        String nickName = user.getNickName();
        questionInfoDTO.setUserId(userId);
        questionInfoDTO.setNickName(StringUtils.defaultString(userName, nickName));
        return questionInfoService.createQuestion(questionInfoDTO);
    }

    @PostMapping("question/edit")
    public IResult edit(@Valid @RequestBody QuestionInfoDTO questionInfoDTO) throws BizException {
        return questionInfoService.editQuestion(questionInfoDTO);
    }

    @PostMapping("question/remove")
    public IResult remove(Integer id)  throws BizException {
        return questionInfoService.removeQuestion(id);
    }

    @PostMapping("question/list")
    public IResult list(QuestionInfoQuery query, @CurrentUser UserEntity userEntity) {
        User user = userEntity.getUser();
        if (StringUtils.isEmpty(query.getUserId())) {
            query.setUserId(user.getUserId());
        }
        List<QuestionInfoVO> examInfos = questionInfoService.queryList(query);
        return ResultList.success(examInfos);
    }

    @PostMapping("question/page")
    public IResult page(QuestionInfoQuery query, @CurrentUser UserEntity userEntity) {
        User user = userEntity.getUser();
        if (StringUtils.isEmpty(query.getUserId())) {
            query.setUserId(user.getUserId());
        }
        PageVO<QuestionInfoVO> pageVO = questionInfoService.queryPage(query);
        return ResultPage.success(pageVO);
    }

    @PostMapping("question/one")
    public IResult one(Integer id) throws BizException {
        QuestionInfoVO one = questionInfoService.findOne(id);
        return Result.success(one);
    }

    @PostMapping("question/exam/query")
    public IResult queryExam(ExamQuestionQuery query, @CurrentUser UserEntity userEntity) throws BizException {
        User user = userEntity.getUser();
        String userId = user.getUserId();
        query.setUserId(userId);
        Integer examId = query.getExamId();
        if (examId == null) {
            return IResult.error(ErrorEnum.ERRCODE_0403, "examId 不能为空");
        }

        PageVO<ExamQuestionVO> pageVO = questionInfoService.pageExamQuestion(query);
        return ResultPage.success(pageVO);
    }

    @PostMapping("question/exam/assign")
    public IResult assignExam(@RequestBody ExamQuestionDTO attrs, List<Integer> questionIds) throws BizException {
        if (attrs.getExamId() == null || attrs.getSorted() == null || attrs.getScore() == null) {
            return IResult.error(ErrorEnum.ERRCODE_0403);
        }
        if (StringUtils.isBlank(attrs.getOp())) {
            return IResult.error(ErrorEnum.ERRCODE_0403);
        }
        if (CollectionUtils.isEmpty(questionIds)) {
            return IResult.error(ErrorEnum.ERRCODE_0403);
        }

        return examQuestionService.assignExamQuestion(attrs, questionIds);

    }

    /**
     * 下载导入模板
     * @param response
     * @throws IOException
     */
    @GetMapping("question/template/download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String timestamp = String.valueOf(System.currentTimeMillis());
        String fileName = URLEncoder.encode("EXMG题目倒入模板" + timestamp, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        List<ImportQuestion> exmpleList = new ArrayList<>();
        exmpleList.add(new ImportQuestion("这是一道单选题", "只有一个正确选项", 1, 1, 1, 101,true, "AAAA", "BBBB", "CCCC", "DDDD", "0"));
        exmpleList.add(new ImportQuestion("这是一道多选题", "有多个正确选项", 2, 1, 1, 101,true, "AAAA", "BBBB", "CCCC", "DDDD", "0,1,2"));
        exmpleList.add(new ImportQuestion("这是一道多选题", "可以没有正确选项", 2, 1, 1, 101,true, "AAAA", "BBBB", "CCCC", "DDDD", ""));
        exmpleList.add(new ImportQuestion("这是一道填空简答", "不需要正确选项", 3, 1, 1, 101,true, "AAAA", "BBBB", "CCCC", "DDDD", ""));

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream()).build();
            WriteSheet sheet0 = EasyExcel.writerSheet(0, "sheet0").build();
            WriteTable writeTable0 = EasyExcel.writerTable(0).head(ImportQuestion.class).needHead(true).build();
            excelWriter.write(exmpleList, sheet0, writeTable0);
            WriteSheet cateSheet = EasyExcel.writerSheet(1, "知识分类表").build();
            WriteTable writeTable1 = EasyExcel.writerTable(0).head(ExportCategory.class).needHead(true).build();
            ExamCategoryEnums[] cateList = ExamCategoryEnums.values();
            List<ExportCategory> exportCategories = new ArrayList<>();
            for (ExamCategoryEnums categoryEnums : cateList) {
                ExportCategory exportCategory = new ExportCategory();
                exportCategory.setId(categoryEnums.getCode());
                exportCategory.setCategoryName(categoryEnums.getMsg());
                exportCategory.setParentId(categoryEnums.getParentId());
                exportCategory.setLevel(categoryEnums.isTop() ? 1 : 2);
                exportCategories.add(exportCategory);
            }

            excelWriter.write(exportCategories, cateSheet, writeTable1);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


    /**
     * 批量上传接口
     * @param uploadFile
     * @param userEntity
     * @return
     * @throws IOException
     */
    @PostMapping("question/template/upload")
    public IResult upload(@RequestParam("file") MultipartFile uploadFile, @CurrentUser UserEntity userEntity) throws IOException {

        InputStream inputStream = uploadFile.getInputStream();
        EasyExcel.read(inputStream, ImportQuestion.class, new ReadListener<ImportQuestion> () {

            /**
             * 读取每一行的回调
             * @param importQuestion
             * @param analysisContext
             */
            @Override
            public void invoke(ImportQuestion importQuestion, AnalysisContext analysisContext) {
                QuestionInfoDTO questionInfoDTO = new QuestionInfoDTO();
                if (StringUtils.isBlank(importQuestion.getQuestion())) {
                    return ;
                }
                questionInfoDTO.setQuestion(importQuestion.getQuestion());
                questionInfoDTO.setInterpretation(importQuestion.getInterpretation());
                Integer questionType = importQuestion.getQuestionType();
                questionInfoDTO.setQuestionType(questionType);
                questionInfoDTO.setDifficulty(importQuestion.getDifficulty());
                questionInfoDTO.setTopCategory(importQuestion.getTopCategory());
                questionInfoDTO.setCategoryId(importQuestion.getCategoryId());
                questionInfoDTO.setPicsUrl("");
                questionInfoDTO.setCommon(importQuestion.getCommon());
                String[] importOptions = new String[] { importQuestion.getOption0(), importQuestion.getOption1(), importQuestion.getOption2(),
                        importQuestion.getOption3(), importQuestion.getOption4(), importQuestion.getOption5(), importQuestion.getOption6(),
                        importQuestion.getOption7(), importQuestion.getOption8(), importQuestion.getOption9()
                };

                List<OptionInfoDTO> options = makeOptionList(importOptions);
                String answer = importQuestion.getAnswer();
                if (answer != null) {
                    String[] answers = StringUtils.split(answer, ',');
                    for (String ans : answers) {
                        Integer index = Integer.valueOf(ans);
                        if (index >= 0 && index < options.size()) {
                            options.get(index).setRight(true);
                        }
                    }
                }
                questionInfoDTO.setOptions(options);
                try {
                    QuestionInfoAPI.this.create(questionInfoDTO, userEntity);
                } catch (BizException ex) {
                    log.error("", ex);
                }
            }

            public List<OptionInfoDTO> makeOptionList(String[] importOptions) {
                List<OptionInfoDTO> options = new ArrayList<>();
                for (String importOption : importOptions) {
                    if (StringUtils.isNotBlank(importOption)) {
                        OptionInfoDTO optionInfo = new OptionInfoDTO();
                        optionInfo.setOption(importOption);
                        optionInfo.setRight(false);
                        options.add(optionInfo);
                    }
                }
                return options;
            }

            /**
             * 完成读操作时的回调
             * @param analysisContext
             */
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
        return Result.success("");

    }

}
