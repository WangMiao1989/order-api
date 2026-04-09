package com.wm.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wm.entity.FileEntity;
import com.wm.exception.SystemException;
import com.wm.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;
	
	@RequestMapping("/image")
	public ResponseEntity<byte[]> getImageFromFile(@RequestParam("id") Long id) {
		FileEntity file =  fileService.getFile(id);
		if(Objects.isNull(file)) {
			throw new SystemException("图片不存在");
		}
		
		return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(file.getContentType()))
					.header(HttpHeaders.CACHE_CONTROL, "max-age=3600")
					.body(file.getContent());
	}
}