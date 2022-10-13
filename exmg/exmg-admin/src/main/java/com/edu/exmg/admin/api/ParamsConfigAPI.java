package com.edu.exmg.admin.api;

import com.alibaba.excel.EasyExcel;
import com.edu.exmg.admin.config.FileUploadConfig;
import com.edu.exmg.admin.config.IgnorePermission;
import com.edu.exmg.admin.vo.ImportQuestion;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author autom
 * @date 2022-01-06
 **/
@Slf4j
@RestController
@RequestMapping("admin")
public class ParamsConfigAPI {

    @Autowired
    private FileUploadConfig uploadConfig;

    @PostMapping("config/savetofile")
    public IResult savetofile(@RequestParam("file") MultipartFile uploadFile) throws IOException {
        String originalFilename = uploadFile.getOriginalFilename();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String random = RandomStringUtils.randomNumeric(6);
        String fileName = timestamp + random+ originalFilename;
        String uploadPath = uploadConfig.getAbsolutePath();
        log.info("uploadPath: {}, fileName: {}", uploadPath, fileName);
        Path savePath = Paths.get(uploadPath, fileName);
        uploadFile.transferTo(savePath);
        String relativePath = uploadConfig.getRelativePath();
        Path relPath = Paths.get(relativePath, fileName);
        return Result.success(relPath.toString());
    }

}
