package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class S3Notify {
    @JsonProperty("EventName")
    public String getEventName() { 
		 return this.eventName; } 
    public void setEventName(String eventName) { 
		 this.eventName = eventName; } 
    String eventName;
    @JsonProperty("Key") 
    public String getKey() { 
		 return this.key; } 
    public void setKey(String key) { 
		 this.key = key; } 
    String key;
    @JsonProperty("Records") 
    public ArrayList<Record> getRecords() {
		 return this.records; } 
    public void setRecords(ArrayList<Record> records) { 
		 this.records = records; } 
    ArrayList<Record> records;

    @Override
    public String toString() {
        return "S3Notify{" +
                "eventName='" + eventName + '\'' +
                ", key='" + key + '\'' +
                ", records=" + records +
                '}';
    }
}
