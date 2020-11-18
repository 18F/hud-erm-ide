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
public class Documents {
	
	@JsonProperty("id")
	private int id;
	@JsonProperty("state")
	private String state;
	@JsonProperty("substate")
	private String substate;
	@JsonProperty("exceptions")
	private List<Object> exceptions;
	@JsonProperty("start_time")
	private String start_time;
	@JsonProperty("complete_time")
	private String complete_time;
	@JsonProperty("priority")
	private int priority;
	@JsonProperty("layout_uuid")
	private String layout_uuid;
	@JsonProperty("layout_name")
	private String layout_name;
	@JsonProperty("layout_tags")
	private List<Object> layout_tags;
	@JsonProperty("layout_version_uuid")
	private String layout_version_uuid;
	@JsonProperty("layout_version_name")
	private String layout_version_name;
	@JsonProperty("document_folders")
	private List<Object> document_folders;
	@JsonProperty("supervision_url")
	private String supervision_url;
	@JsonProperty("type")
	private String type;
	//private List<Object> metadata;//ISSUE HERE EITHER null or [] is expected but has {}
	@JsonProperty("metadata")
	private BlankClass metadata;
	@JsonProperty("pages")
	private List<Pages> pages;
	@JsonProperty("document_tables")
	private List<Object> document_tables;
	@JsonProperty("document_fields")
	private List<DocumentFields> document_fields;
	@JsonProperty("derived_document_fields")
	private List<Object> derived_document_fields;
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
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getComplete_time() {
		return complete_time;
	}
	public void setComplete_time(String complete_time) {
		this.complete_time = complete_time;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getLayout_uuid() {
		return layout_uuid;
	}
	public void setLayout_uuid(String layout_uuid) {
		this.layout_uuid = layout_uuid;
	}
	public String getLayout_name() {
		return layout_name;
	}
	public void setLayout_name(String layout_name) {
		this.layout_name = layout_name;
	}
	public List<Object> getLayout_tags() {
		return layout_tags;
	}
	public void setLayout_tags(List<Object> layout_tags) {
		this.layout_tags = layout_tags;
	}
	public String getLayout_version_uuid() {
		return layout_version_uuid;
	}
	public void setLayout_version_uuid(String layout_version_uuid) {
		this.layout_version_uuid = layout_version_uuid;
	}
	public String getLayout_version_name() {
		return layout_version_name;
	}
	public void setLayout_version_name(String layout_version_name) {
		this.layout_version_name = layout_version_name;
	}
	public List<Object> getDocument_folders() {
		return document_folders;
	}
	public void setDocument_folders(List<Object> document_folders) {
		this.document_folders = document_folders;
	}
	public String getSupervision_url() {
		return supervision_url;
	}
	public void setSupervision_url(String supervision_url) {
		this.supervision_url = supervision_url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BlankClass getMetadata() {
		return metadata;
	}
	public void setMetadata(BlankClass metadata) {
		this.metadata = metadata;
	}
	public List<Pages> getPages() {
		return pages;
	}
	public void setPages(List<Pages> pages) {
		this.pages = pages;
	}
	public List<Object> getDocument_tables() {
		return document_tables;
	}
	public void setDocument_tables(List<Object> document_tables) {
		this.document_tables = document_tables;
	}
	public List<DocumentFields> getDocument_fields() {
		return document_fields;
	}
	public void setDocument_fields(List<DocumentFields> document_fields) {
		this.document_fields = document_fields;
	}
	public List<Object> getDerived_document_fields() {
		return derived_document_fields;
	}
	public void setDerived_document_fields(List<Object> derived_document_fields) {
		this.derived_document_fields = derived_document_fields;
	}
	
	
	
}
