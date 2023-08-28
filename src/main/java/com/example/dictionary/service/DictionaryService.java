package com.example.dictionary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dictionary.database.DataBaseWorker;
import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;


public class DictionaryService {
	
	DataBaseWorker dbw = new DataBaseWorker();
	
	public List<Dictionary> getAllDictionaries() {
		
		List<Dictionary> dictList = dbw.getAllDictionaries();
		return dictList;
	}
	
	public boolean createDictionary(Dictionary dict) {
		dict.readDictionaryFile();
		dbw.createDictionary(dict);
		return true;
	}
	
	public boolean saveDictionary(Dictionary dict) {
		dbw.saveDictionary(dict);
		return true;
	}
	
	public List<Word> getAllWords(Long id) {
		return dbw.getAllWords(id);
	}
	
	public Dictionary getDictById(Long id) {
		return dbw.getDictById(id);
	}
	
	public boolean deleteDictionary(Long id) {
		
		dbw.deleteDictionary(id);
		return true;
	}
	
	public Word getWordById(Long id) {
		return dbw.getWordById(id);
	}
	
	public boolean saveWord(Word word) {
		if(word.getKeyWord().length() <= 32 && word.getValueWord().length() <= 32)
			return dbw.saveWord(word);
		return false;
	}
	
	public boolean deleteWord(Long id) {
		return dbw.deleteWord(id);
	}
	
	public boolean createWord(Word word) {
		return dbw.createWord(word);
	}
	
	public boolean deleteWordByKey(Word word) {
		return dbw.deleteWordByKey(word);
	}
	
	public List<Word> findWords(Word word) {
		return dbw.findWords(word);
	}
	
	public List<Dictionary> findDicts(Dictionary dict) {
		return dbw.findDicts(dict);
	}
}