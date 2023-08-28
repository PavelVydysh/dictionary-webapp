package com.example.dictionary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;
import com.example.dictionary.service.DictionaryService;

@Controller
public class DictionaryController {
	
	DictionaryService dictService = new DictionaryService();
	
	@GetMapping({"/", "/viewDictionariesList"})
	public String viewDictionaryList(@ModelAttribute("message") String message, Model model) {
		
		model.addAttribute("dictionariesList", dictService.getAllDictionaries());
		model.addAttribute("message", message);
		model.addAttribute("dictF", new Dictionary());
		return "ViewDictionariesList";
	}	
	
	@GetMapping("/viewFindDictionary")
	public String viewFindDictionary(Dictionary dictF, Model model) {
		model.addAttribute("dictionariesList", dictService.findDicts(dictF));
		model.addAttribute("dictF", new Dictionary());
		return "ViewDictionariesList";
	}
	
	@GetMapping("/addDictionary")
	public String addDictionary(@ModelAttribute("message") String message, Model model) {
		model.addAttribute("dict", new Dictionary());
		model.addAttribute("message", message);
		
		return "AddDictionary";
	}
	
	@PostMapping("/createDictionary")
	public String saveDictionary(@RequestParam("file") MultipartFile file, Dictionary dict, RedirectAttributes redirectAttributes) {
		if(dictService.createDictionary(dict)) {
			dict.setFile(file);
			redirectAttributes.addFlashAttribute("message", "save success");
			return "redirect:/viewDictionariesList";
		}
		
		redirectAttributes.addFlashAttribute("message", "save failed");
		return "redirect:/addDictionary";
	}
	
	@GetMapping("/editDictionary/{id}")
	public String editDictionary(@PathVariable Long id, Model model) {
		model.addAttribute("dict", dictService.getDictById(id));
		
		return "EditDictionary";
	}	
	
	@PostMapping("/editSaveDictionary")
	public String editSaveDictionary(Dictionary dict, RedirectAttributes redirectAttributes) {
		dictService.saveDictionary(dict);
		return "redirect:/viewDictionariesList";
	}
	
	@GetMapping("/openDictionary/{id}")
	public String openDictionary(@PathVariable Long id, Model model) {
		model.addAttribute("words", dictService.getAllWords(id));
		model.addAttribute("dict", dictService.getDictById(id));
		Word newWord = new Word();
		newWord.setIdDict(id);
		model.addAttribute("newWord", newWord);
		return "OpenDictionary";
	}
	
	@GetMapping("/deleteDictionary/{id}")
	public String deleteDictionary(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if(dictService.deleteDictionary(id)) {
			redirectAttributes.addFlashAttribute("message", "delete success");
		}
		
		redirectAttributes.addFlashAttribute("message", "delete failed");
		return "redirect:/viewDictionariesList";
	}
	
	@GetMapping("/editWord/{id}")
	public String editWord(@PathVariable Long id, Model model) {
		model.addAttribute("word", dictService.getWordById(id));
		
		return "EditWord";
	}
	
	@PostMapping("/editSaveWord")
	public String editSaveWord(Word word, RedirectAttributes redirectAttributes) {
		dictService.saveWord(word);
		return "redirect:/openDictionary/" + word.getIdDict();
	}
	
	@GetMapping("/deleteWord/{id}")
	public String deleteWord(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		Long dictId = dictService.getWordById(id).getIdDict();
		if(dictService.deleteWord(id)) {
			redirectAttributes.addFlashAttribute("message", "delete success");
		}
		
		redirectAttributes.addFlashAttribute("message", "delete failed");
		return "redirect:/openDictionary/" + dictId;
	}
	
	@PostMapping("/createWord")
	public String createWord(Word word, RedirectAttributes redirectAttributes) {
		dictService.createWord(word);
		return "redirect:/openDictionary/" + word.getIdDict();
	}
	
	@PostMapping("/deleteWordByKey")
	public String deleteWordByKey(Word word, RedirectAttributes redirectAttributes) {
		dictService.deleteWordByKey(word);
		return "redirect:/openDictionary/" + word.getIdDict();
	}
	
	@GetMapping("/findWords")
	public String findWords(Word word, Model model) {
		System.out.println(word.getKeyWord() + word.getIdDict());
		model.addAttribute("finds", dictService.findWords(word));
		model.addAttribute("dict", word.getIdDict());
		model.addAttribute("newWord", word);
		return "FindWords";
	}
}
