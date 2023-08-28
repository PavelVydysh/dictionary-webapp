package com.example.dictionary.model;

import jakarta.persistence.*;


public class Word {
	
	
	private Long id;
	

	private Long idDict;
	

	private String keyWord;
	

	private String valueWord;
	
	public Word() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdDict() {
		return idDict;
	}
	
	public void setIdDict(Long idDict) {
		this.idDict = idDict;
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public String getValueWord() {
		return valueWord;
	}
	
	public void setValueWord(String valueWord) {
		this.valueWord = valueWord;
	}
}
