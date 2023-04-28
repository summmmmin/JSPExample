package com.yedam.prod.control;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.FrontControl.Control;

public class ProductUploadControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String savePath = req.getServletContext().getRealPath("images");	//이미지 경로
		String fileName = "";	
		String fileUrl = "";
		
		MultipartRequest multi = new MultipartRequest(req, savePath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());	//maven cos 추가
		
		Enumeration<?> files = multi.getFileNames();	// 업로드된 파일 정보 
		while (files.hasMoreElements()) {
			String file = (String) files.nextElement();
			System.out.println("file: "+file);
			fileName = multi.getFilesystemName(file);
		}
		fileUrl = req.getContextPath() + "/images/" + fileName;
		
		// ckeditor 정보를 확인해서 정보를 에디터 부분에 출력하기 위해
		JsonObject json = new JsonObject();	//maven gson추가
		json.addProperty("uploaded", 1);
		json.addProperty("fileName", fileName);
		json.addProperty("url", fileUrl);
		
		return json + ".json";
	}

}
