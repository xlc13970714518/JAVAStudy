package com.xlc.demo.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    public static void main(String[] args) throws IllegalAccessException, IOException {
        String fileName = "test.txt";
        MultipartFile multipartFile = fileToMultipartFile(fileName);
        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println(multipartFile.getName());
        System.out.println(originalFilename);
        System.out.println(new String(multipartFile.getBytes(), StandardCharsets.UTF_8));
    }

    public static CommonsMultipartFile fileToMultipartFile(String fileName) throws IllegalAccessException {
        File file = FileUtils.getFile(fileName);
        // 创建一个fileItem MediaType 对于  MULTIPART_FORM_DATA_VALUE = "multipart/form-data";
        FileItem item = new DiskFileItemFactory().createItem("file",
                MediaType.MULTIPART_FORM_DATA_VALUE, true, fileName);
        try (InputStream input = IOUtils.toInputStream(JSON.toJSONString("hello MultipartFile"),
                StandardCharsets.UTF_8.name()); OutputStream outputStream = item.getOutputStream()){
            // 流转移
            IOUtils.copy(input, outputStream, 1024*1024);
        } catch (Exception e) {
            throw new IllegalAccessException("FileItem Exception");
        }
        return new CommonsMultipartFile(item);
    }
}
