package com.ashok.lyndaPractices.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.locks.Condition;

import android.content.Context;
import android.content.SharedPreferences;

public class NotesDataSource {
	
	private static final String PREFKEY= "notes";
	private SharedPreferences noteprefs; 
	
	public NotesDataSource(Context context){
		noteprefs = context.getSharedPreferences(PREFKEY, Context.MODE_PRIVATE);
	}

	public List<NoteItem> findAll(){
		
		Map<String, ?> notesMap = noteprefs.getAll();
		SortedSet<String> keys = new TreeSet<String>(notesMap.keySet());
		List<NoteItem> notelist = new ArrayList<NoteItem>();
		for (String key : keys) {
			
			NoteItem note = new NoteItem();
			note.setKey(key);
			note.setText((String) notesMap.get(key));
			notelist.add(note);
			
		} 
		return notelist;
	}
	
	public boolean update(NoteItem note){
		SharedPreferences.Editor editor = noteprefs.edit();
		editor.putString(note.getKey(), note.getText());
		editor.commit();
		return true; 
	}
	
	public boolean remove(NoteItem note){
		
		if(noteprefs.contains(note.getKey())){
			
	
		SharedPreferences.Editor editor = noteprefs.edit();
		editor.remove(note.getKey());
		editor.commit();
		
		}
		return true;
	}
}

