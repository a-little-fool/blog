package com.zss.blog.controller;

import com.zss.blog.utils.QiniuUtils;
import com.zss.blog.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author 周书胜
 * @date 2023年04月23 17:08
 */

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping
    public ResultVo upload(@RequestParam("image") MultipartFile multipartFile) {
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
        boolean upload = qiniuUtils.upload(multipartFile, fileName);
        if (upload) {
            return ResultVo.success(QiniuUtils.url + fileName);
        }
        return ResultVo.fail(20001, "上传失败");
    }

}
