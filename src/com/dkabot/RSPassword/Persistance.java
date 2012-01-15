package com.dkabot.RSPassword;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotEmpty;

@Entity()
@Table(name = "RSPassword")
public class Persistance {

    @Id
    private int id;
    @NotEmpty
    private String creatorName;
    @NotEmpty
    private String password;
    private String location;
    

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


}