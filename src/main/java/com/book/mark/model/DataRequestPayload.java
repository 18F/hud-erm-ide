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
public class DataRequestPayload {
	
	@JsonProperty("id")
	private int id;
	@JsonProperty("external_id")
	private String external_id;
	@JsonProperty("state")
	private String state;
	@JsonProperty("substate")
	private String substate;
	@JsonProperty("exceptions")
	private List<Object> exceptions;
	@JsonProperty("halted")
	private boolean halted;
	@JsonProperty("start_time")
	private String start_time;
	@JsonProperty("complete_time")
	private String complete_time;
	@JsonProperty("supervision_url")
	private String supervision_url;
	@JsonProperty("metadata")
	private String metadata;
	@JsonProperty("data_deleted")
	private boolean data_deleted;
	@JsonProperty("submission_files")
	private List<SubmissionFiles> submission_files;
	@JsonProperty("document_folders")
	private List<Object> document_folders;
	@JsonProperty("documents")
	private List<Documents> documents;
	@JsonProperty("unassigned_pages")
	private List<Object> unassigned_pages;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExternal_id() {
		return external_id;
	}

	public void setExternal_id(String external_id) {
		this.external_id = external_id;
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

	public boolean isHalted() {
		return halted;
	}

	public void setHalted(boolean halted) {
		this.halted = halted;
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

	public String getSupervision_url() {
		return supervision_url;
	}

	public void setSupervision_url(String supervision_url) {
		this.supervision_url = supervision_url;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public boolean isData_deleted() {
		return data_deleted;
	}

	public void setData_deleted(boolean data_deleted) {
		this.data_deleted = data_deleted;
	}

	public List<SubmissionFiles> getSubmission_files() {
		return submission_files;
	}

	public void setSubmission_files(List<SubmissionFiles> submission_files) {
		this.submission_files = submission_files;
	}

	public List<Object> getDocument_folders() {
		return document_folders;
	}

	public void setDocument_folders(List<Object> document_folders) {
		this.document_folders = document_folders;
	}

	public List<Documents> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}

	public List<Object> getUnassigned_pages() {
		return unassigned_pages;
	}

	public void setUnassigned_pages(List<Object> unassigned_pages) {
		this.unassigned_pages = unassigned_pages;
	}
	
	
}
