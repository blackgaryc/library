package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class S3Notify implements Serializable {
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
    public List<Record> getRecords() {
		 return this.records; } 
    public void setRecords(List<Record> records) {
		 this.records = records; } 
    List<Record> records;

    @Override
    public String toString() {
        return "S3Notify{" +
                "eventName='" + eventName + '\'' +
                ", key='" + key + '\'' +
                ", records=" + records +
                '}';
    }
}
