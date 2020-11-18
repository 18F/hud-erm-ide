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
public class DocumentFields {
	
	@JsonProperty("id")
	private int id;
	@JsonProperty("state")
	private String state;
	@JsonProperty("substate")
	private String substate;
	@JsonProperty("exceptions")
	private List<Object> exceptions;
	@JsonProperty("name")
	private String name;
	@JsonProperty("output_name")
	private String output_name;
	@JsonProperty("field_definition_attributes")
	private FieldDefinitionAttributes field_definition_attributes;
	@JsonProperty("transcription")
	private Transcription transcription;
	@JsonProperty("field_image_url")
	private String field_image_url;
	@JsonProperty("page_id")
	private int page_id;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOutput_name() {
		return output_name;
	}
	public void setOutput_name(String output_name) {
		this.output_name = output_name;
	}
	public FieldDefinitionAttributes getField_definition_attributes() {
		return field_definition_attributes;
	}
	public void setField_definition_attributes(FieldDefinitionAttributes field_definition_attributes) {
		this.field_definition_attributes = field_definition_attributes;
	}
	public Transcription getTranscription() {
		return transcription;
	}
	public void setTranscription(Transcription transcription) {
		this.transcription = transcription;
	}
	public String getField_image_url() {
		return field_image_url;
	}
	public void setField_image_url(String field_image_url) {
		this.field_image_url = field_image_url;
	}
	public int getPage_id() {
		return page_id;
	}
	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}
	
	
}
