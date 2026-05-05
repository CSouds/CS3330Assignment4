package edu.oop.guild.log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuildLog {
	private static final GuildLog INSTANCE = new GuildLog();
    private final List<String> entryList = new ArrayList<>();
    
    private GuildLog() {}	
    
    public static GuildLog getInstance() {
        return INSTANCE;
    }
    
    public void record(String entry)      // throws NullPointerException if null
    {
    	if (entry == null) {
            throw new NullPointerException();
        }
    	entryList.add(entry);
    }
    
    public List<String> entries()         // returns unmodifiable list
    {
    	return Collections.unmodifiableList(entryList);
    }
    
    public int size()
    {
    	return entryList.size();
    }
    
    public void clear()
    {
    	entryList.clear();
    }
}