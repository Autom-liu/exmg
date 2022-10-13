package com.edu.exmg.admin.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author autom
 * @date 2022-01-06
 **/
@Configuration
@Data
public class FileUploadConfig {

    @Value("${files.upload.absolutePath}")
    private String absolutePath;

    @Value("${files.upload.relativePath}")
    private String relativePath;

}
