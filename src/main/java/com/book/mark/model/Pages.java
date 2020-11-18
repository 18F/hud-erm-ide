package com.book.mark.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pages {
	@JsonProperty("id")
	private int id;
	@JsonProperty("state")
	private String state;
	@JsonProperty("substate")
	private String substate;
	@JsonProperty("exceptions")
	private List<Object> exceptions;
	@JsonProperty("page_type")
	private String page_type;
	@JsonProperty("file_page_number")
	private int file_page_number;
	@JsonProperty("submission_page_number")
	private int submission_page_number;
	@JsonProperty("layout_page_number")
	private int layout_page_number;
	@JsonProperty("submitted_filename")
	private String submitted_filename;
	@JsonProperty("image_url")
	private String image_url;
	@JsonProperty("corrected_image_url")
	private String corrected_image_url;
	@JsonProperty("rejected")
	private boolean rejected;
	@JsonProperty("identifiers")
	private List<Object> identifiers;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSubstate() {
		return substate;
	}
	public void setSubstate(String substate) {
		this.substate = substate;
	}
	public List<Object> getExceptions() {
		return exceptions;
	}
	public void setExceptions(List<Object> exceptions) {
		this.exceptions = exceptions;
	}
	public String getPage_type() {
		return page_type;
	}
	public void setPage_type(String page_type) {
		this.page_type = page_type;
	}
	public int getFile_page_number() {
		return file_page_number;
	}
	public void setFile_page_number(int file_page_number) {
		this.file_page_number = file_page_number;
	}
	public int getSubmission_page_number() {
		return submission_page_number;
	}
	public void setSubmission_page_number(int submission_page_number) {
		this.submission_page_number = submission_page_number;
	}
	public int getLayout_page_number() {
		return layout_page_number;
	}
	public void setLayout_page_number(int layout_page_number) {
		this.layout_page_number = layout_page_number;
	}
	public String getSubmitted_filename() {
		return submitted_filename;
	}
	public void setSubmitted_filename(String submitted_filename) {
		this.submitted_filename = submitted_filename;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getCorrected_image_url() {
		return corrected_image_url;
	}
	public void setCorrected_image_url(String corrected_image_url) {
		this.corrected_image_url = corrected_image_url;
	}
	public List<Object> getIdentifiers() {
		return identifiers;
	}
	public void setIdentifiers(List<Object> identifiers) {
		this.identifiers = identifiers;
	}
	
	
}
