package com.example.dictionary.model;

import java.io.*;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;


public class Dictionary {
	
	
	private Long id;
	

	private String name;
	
	private MultipartFile file;
	
	private MultiMap<String, String> dictMap = new MultiMap<String, String>();
	
	public Dictionary() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public MultiMap<String, String> getMap() {
		return dictMap;
	}
	
	public boolean readDictionaryFile() {
        
            try {
            		
            		byte[] bytes = file.getBytes();
                	File currentFile = new File("dict.txt");
                	BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(currentFile));
                	stream.write(bytes);
                	stream.flush();
                	stream.close();
                    BufferedReader reader = new BufferedReader(new FileReader(currentFile));
                    String line = reader.readLine();

                    while(line != null) {
                        String[] splitLine = line.split("-");
                        dictMap.put(splitLine[0], splitLine[1]);
                        line = reader.readLine();
                   }

                    reader.close();
            	
                
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
            return true;
    }
}
