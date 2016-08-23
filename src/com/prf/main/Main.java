package com.prf.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.prf.file.FileOpr;


public class Main {
	
	static String splitString = "woshipanruifeng";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 String path="/Users/dapan/Downloads/MyPro/src/prf/";
		 Map<String, String> fileNames = new HashMap<String, String>();
		 ArrayList<String> paths = FileOpr.readFile("paths.txt");
		 for(String path : paths) {
			 getFileName(path,fileNames);
		 }
		 for(Map.Entry<String, String> fileName : fileNames.entrySet()) {
			 if (fileName.getValue().contains(splitString)) {
				FileOpr.writeFile("report.txt", fileName.getKey() + "\n", true);
				String[] temp = fileName.getValue().split(splitString);
				for (int i = 0; i < temp.length; i++) {
					FileOpr.writeFile("report.txt", temp[i] + "\n", true);
				}
				FileOpr.writeFile("report.txt", "\n", true);
				
			}
		 }
	}
	
	public static void getFileName(String filePath,Map<String, String> fileNames) {
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		if (files == null ||files.length == 0) {
			return;
		}
		for(File file : files) {
			if(file.isDirectory()) {
				getFileName(filePath + file.getName() + "/", fileNames);
			} else {
				if (fileNames.get(file.getName()) != null) {
					fileNames.put(file.getName(), fileNames.get(file.getName()) + splitString + filePath);
				} else {
					fileNames.put(file.getName(), filePath);
				}
			}
		}
	}
}
