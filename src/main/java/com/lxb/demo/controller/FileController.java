package com.lxb.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lxb.demo.commons.FileUtils;

@RestController("/file")
public class FileController {

	@PostMapping(value = "/upload")
	public String uploadFile(HttpServletRequest req, @RequestParam("file") MultipartFile file) {

		FileUtils.uploadFile(file);

		return "";
	}
}
