package com.book.mark.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmissionId {

	@JsonProperty(value = "submission_id")
	private String submissionId;

	public String getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(String submissionId) {
		this.submissionId = submissionId;
	}
	
}
