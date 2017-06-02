package com.me.business;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class ImageValidator {

	 private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
	
	public String validateImage(MultipartFile photo)
	{
		String message="";
		   Pattern pattern = Pattern.compile(IMAGE_PATTERN);
	       Matcher matcher;
	       matcher = pattern.matcher(photo.getOriginalFilename());
	       if(0 == photo.getSize()) {
	           message="Photo Can not Be Empty";
	        }
	              if(!matcher.matches()) {
	            	  message="Invalid Photo Type";
	        }
	        
	        if(5000000 < photo.getSize()) {
	        	message="File size is over 5mb !";
	        }
	        return message;
	}
	
	
	public String saveImage(MultipartFile photo)
	{
		//C:\Users\chikk\OneDrive\Documents\FarmersAid\src\main\webapp\resources\images
		   String hc=new String("C:\\Users\\chikk\\OneDrive\\Documents\\FarmersAid\\src\\main\\webapp\\resources\\images\\");
           String dataBasePath = "/resources/images/";
           String fileNameWithExt = System.currentTimeMillis() + photo.getOriginalFilename();
           File file = new File(hc + fileNameWithExt);
           //String context = request.getContextPath();
           //System.out.println("path to image" + path + fileNameWithExt);
           try {
			photo.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           return dataBasePath+fileNameWithExt;
	}
	
	
}
