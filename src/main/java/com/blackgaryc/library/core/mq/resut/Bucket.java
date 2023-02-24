package com.blackgaryc.library.core.mq.resut;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Bucket implements Serializable {
    @JsonProperty("name")
    public String getName() { 
		 return this.name; } 
    public void setName(String name) { 
		 this.name = name; } 
    String name;
    @JsonProperty("ownerIdentity") 
    public OwnerIdentity getOwnerIdentity() { 
		 return this.ownerIdentity; } 
    public void setOwnerIdentity(OwnerIdentity ownerIdentity) { 
		 this.ownerIdentity = ownerIdentity; } 
    OwnerIdentity ownerIdentity;
    @JsonProperty("arn") 
    public String getArn() { 
		 return this.arn; } 
    public void setArn(String arn) { 
		 this.arn = arn; } 
    String arn;

    @Override
    public String toString() {
        return "Bucket{" +
                "name='" + name + '\'' +
                ", ownerIdentity=" + ownerIdentity +
                ", arn='" + arn + '\'' +
                '}';
    }
}
