package com.award.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.award.model.FileModel;
import com.award.repository.FileRepository;

import lombok.extern.slf4j.Slf4j;

 
@RestController
@Slf4j
public class UploadFileController {
	@Autowired
	FileRepository fileRepository; 
    /*
     * MultipartFile Upload
     */
    @PostMapping("/api/file/upload")
  
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file) {
    	try {
    		// save file to PostgreSQL
	    	FileModel filemode = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
	    	fileRepository.save(filemode);
	    	
	    	log.trace("A TRACE Message");
			log.debug("A DEBUG Message");
			log.info("An INFO Message");
			log.warn("A WARN Message");
			log.error("An ERROR Message");
	    	return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
		} catch (	Exception e) {
			return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
		}    
    }
}