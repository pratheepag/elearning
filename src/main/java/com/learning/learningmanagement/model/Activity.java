package com.learning.learningmanagement.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "activity")
public class Activity implements Serializable {

	private static final long serialVersionUID = 74458L;

	@Id
	@GeneratedValue
	@Column(name = "activity_id")
	private Long id;

	@Size(min = 3, max = 200, message = "About Me must be between 3 and 200 characters")
	@Column(name = "file_name")
	private String file_name;

	@Column(name = "description")
	private String description;

	@Column(name = "file_type")
	private String file_type;

	@Column(name = "uploaded_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploaded_time;

	@Column(name = "owner")
	private String owner;

	public String getDescription() {
		return description;
	}

	public String getFile_name() {
		return file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public Long getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	public Date getUploaded_time() {
		return uploaded_time;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setFile_type(String string) {
		this.file_type = string;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setUploaded_time(Date uploaded_time) {
		this.uploaded_time = uploaded_time;
	}

}
