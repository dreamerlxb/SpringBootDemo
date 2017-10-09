package com.lxb.demo.commons;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件相关操作
 * @author lxb
 *
 */
public class FileUtils {
	public static String uploadFile(MultipartFile file) {
		// 获取上传文件的路径
		String uploadFilePath = file.getOriginalFilename();
		System.out.println("文件路径 --> " + uploadFilePath);
		// 截取上传文件的文件名
		String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1,
				uploadFilePath.indexOf('.'));
		System.out.println("文件名 --> " + uploadFileName);
		// 截取上传文件的后缀
		String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
		System.out.println("文件后缀 --> " + uploadFileSuffix);

		File dest = new File(".//uploadFiles//" + uploadFileName + "." + uploadFileSuffix);
		
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			return "文件上传失败";
		} catch (IOException e) {
			return "文件上传失败";
		}
		return "文件上传成功";
	}
}
