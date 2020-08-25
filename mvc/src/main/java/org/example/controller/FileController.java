package org.example.controller;

import org.example.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping(value = {"/files"})
public class FileController {
    @Autowired
    private FileService fileService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    String bucketName = "jyang-s3-bucket-test";

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file){
        logger.info("test file name:"+file.getOriginalFilename());
        try {
            String hashedName = fileService.uploadFileUUID(bucketName, file);
            return "The file name: " + hashedName + " has been uploaded successfully :)";
        } catch (IOException e) {
           logger.error("upload file error",e);
           return null;
        }
    }
    // {prefix}/files?filename=docker-commands914e0520-8871-44df-aafd-21a5ef557f1e.txt
    @RequestMapping(value = "", method = RequestMethod.GET,params = {"filename"})
    public String getObject(@RequestParam(name = "filename") String s3key){
        return fileService.getURL(s3key);
    }
}
