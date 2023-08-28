package com.example.dictionary.database;

import java.util.*;

import com.example.dictionary.model.Dictionary;
import com.example.dictionary.model.Word;

import java.sql.*;

public class DataBaseWorker {
	private static final String URL = "jdbc:postgresql://localhost:5433/dictionaryApp";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "root";
	
	private static Connection connection;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Driver");
		}
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} 
		catch (SQLException e) {
			System.out.println("Connection");
		}
	}
	
	public List<Dictionary> getAllDictionaries() {
		ArrayList<Dictionary> dicts = new ArrayList<Dictionary>();
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM \"dictionary\" ORDER BY id ASC";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				Dictionary dict = new Dictionary();
				dict.setId(result.getLong("id"));
				dict.setName(result.getString("name"));
				dicts.add(dict);
			}
			statement.close();
			return dicts;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean createDictionary(Dictionary dict) {
		try {
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO \"dictionary\" VALUES(default,'" + dict.getName() + "')";
			statement.executeUpdate(sql);
			statement.close();
			System.out.println(dict.getMap().keySet() + "sad");
			createWords(dict);
			return true;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean createWords(Dictionary dict) {
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT MAX(id) as id FROM \"dictionary\"";
			ResultSet result = statement.executeQuery(sql);
			result.next();
			int id = result.getInt("id");
			ArrayList<String> keys = new ArrayList<String>(dict.getMap().keySet());
			System.out.println(keys);
			for(int i = 0;i<keys.size();i++) {
				ArrayList<String> values = new ArrayList<String>(dict.getMap().get(keys.get(i)));
				System.out.println(values);
				for(int j = 0; j<values.size(); j++) {
					sql = "INSERT INTO \"word\" VALUES(default,"+ id + ",'" + keys.get(i) + "','" + values.get(j) + "')";
					System.out.println(dict.getId() + " " + keys.get(i) + " " + values.get(j));
					statement.executeUpdate(sql);
					System.out.println("tut");
				}
			}
			statement.close();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteDictionary(Long id) {
		try {
			Statement statement = connection.createStatement();
			String sql = "DELETE FROM \"dictionary\" WHERE \"id\"=" + id;
			statement.executeUpdate(sql);
			statement.close();
			return true;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public List<Word> getAllWords(Long id) {
		ArrayList<Word> words = new ArrayList<Word>();
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM \"word\" WHERE \"id_dict\"="+id + " ORDER BY id ASC";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				Word word = new Word();
				word.setId(result.getLong("id"));
				word.setIdDict(id);
				word.setKeyWord(result.getString("key_word"));
				word.setValueWord(result.getString("value_word"));
				words.add(word);
			}
			statement.close();
			return words;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Dictionary getDictById(Long id) {
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM \"dictionary\" WHERE id=" + id;
			ResultSet result = statement.executeQuery(sql);
			result.next();
			Dictionary dict = new Dictionary();
			dict.setId(id);
			dict.setName(result.getString("name"));
			statement.close();
			return dict;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean saveDictionary(Dictionary dict) {
		try {
			Statement statement = connection.createStatement();
			String sql = "UPDATE \"dictionary\" SET \"name\"='" + dict.getName() + "' WHERE id=" + dict.getId();
			statement.executeUpdate(sql);
			statement.close();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public Word getWordById(Long id) {
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM \"word\" WHERE id=" + id;
			ResultSet result = statement.executeQuery(sql);
			result.next();
			Word word = new Word();
			word.setId(id);
			word.setIdDict(result.getLong("id_dict"));
			word.setKeyWord(result.getString("key_word"));
			word.setValueWord(result.getString("value_word"));
			statement.close();
			return word;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean saveWord(Word word) {
		try {
			Statement statement = connection.createStatement();
			String sql = "UPDATE \"word\" SET \"key_word\"='" + word.getKeyWord() + "', \"value_word\"='" + word.getValueWord() + "' WHERE id=" + word.getId();
			statement.executeUpdate(sql);
			statement.close();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteWord(Long id) {
		try {
			Statement statement = connection.createStatement();
			String sql = "DELETE FROM \"word\" WHERE \"id\"=" + id;
			statement.executeUpdate(sql);
			statement.close();
			return true;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean createWord(Word word) {
		try {
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO \"word\" VALUES(default," + word.getIdDict() + ",'" + word.getKeyWord() + "','" + word.getValueWord() + "')";
			statement.executeUpdate(sql);
			statement.close();
			return true;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteWordByKey(Word word) {
		try {
			Statement statement = connection.createStatement();
			String sql = "DELETE FROM \"word\" WHERE \"key_word\"='" + word.getKeyWord() + "' AND id_dict=" + word.getIdDict();
			statement.executeUpdate(sql);
			statement.close();
			return true;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public List<Word> findWords(Word word) {
		try {
			List<Word> words = new ArrayList<Word>();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM \"word\" WHERE \"id_dict\"=" + word.getIdDict() + " AND (\"key_word\" LIKE '%" + word.getKeyWord() + "%' OR \"value_word\" LIKE '%" + word.getKeyWord() + "%')";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				Word wordAns = new Word();
				wordAns.setId(result.getLong("id"));
				wordAns.setIdDict(word.getIdDict());
				wordAns.setKeyWord(result.getString("key_word"));
				wordAns.setValueWord(result.getString("value_word"));
				words.add(wordAns);
				System.out.println(wordAns.getId() + " " + wordAns.getKeyWord() + " " + wordAns.getValueWord());
			}
			statement.close();
			return words;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<Dictionary> findDicts(Dictionary dict) {
		try {
			List<Dictionary> dicts = new ArrayList<Dictionary>();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM \"dictionary\" WHERE \"name\" LIKE '%" + dict.getName() + "%'";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				Dictionary dictN = new Dictionary(); 
				dictN.setId(result.getLong("id"));
				dictN.setName(result.getString("name"));
				dicts.add(dictN);
			}
			statement.close();
			return dicts;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
