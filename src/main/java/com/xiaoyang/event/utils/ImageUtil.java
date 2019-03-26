package com.xiaoyang.event.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.xiaoyang.event.constant.OSS;


public class ImageUtil {
	
	public static String uploadImage(MultipartFile imageFile) {
		String fileName = imageFile.getOriginalFilename();
		int spotIndex = fileName.lastIndexOf(".");
		fileName = UUIDUtil.getUUID()+fileName.substring(spotIndex);
		String filePath = DateUtil.getDatePath()+fileName;
		InputStream content;
		OSSClient client = new OSSClient(OSS.END_POINT, OSS.ACCESS_KEY_ID, OSS.ACCESS_KEY_SECRET);
		try {
			content = imageFile.getInputStream();
			PutObjectResult result = client.putObject(OSS.BUCKET_PIC, filePath, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			client.shutdown();
		}
		return filePath;
	}
	
}
