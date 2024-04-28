package com.teamtime.tt.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

public class SaveAttachedFile {
	
	public static HashMap<String, String> saveFile(MultipartFile file
			, HttpServletRequest request) {
		String filePath = "";
		HashMap<String, String> fileMap = new HashMap<String, String>();
		String root = request.getSession().getServletContext().getRealPath("images");
		String savePath = root + "\\uUploadFiles";
		File folder = new File(savePath);
		if (!folder.exists())
			folder.mkdir();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String originalFileName = file.getOriginalFilename();
		String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
		String renameFileName = sdf.format(new Date(System.currentTimeMillis())) + extensionName;
		filePath = folder + "\\" + renameFileName;
		fileMap.put("filePath", filePath);
		fileMap.put("fileName", renameFileName);
		try {
			file.transferTo(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileMap;
	}
	
	public static void deleteFile(String filePath, HttpServletRequest request) {
		File deleteFile = new File(filePath);
		if(deleteFile.exists()) {
			// 파일이 존재하면 파일 삭제
			deleteFile.delete();
		}
	}
	
}
