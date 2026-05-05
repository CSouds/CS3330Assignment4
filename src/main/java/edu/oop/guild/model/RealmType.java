package edu.oop.guild.model;

public enum RealmType {
	SKY("Sky Kingdom"),
	UNDERGROUND("Underground Market");
	
	private final String label;
	
    RealmType(String label)
    {
    	this.label = label;
    }
    
    public String displayName()
    {
    	return label;
    }
}
