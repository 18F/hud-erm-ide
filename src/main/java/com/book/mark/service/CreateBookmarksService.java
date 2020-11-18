package com.book.mark.service;

import org.springframework.http.ResponseEntity;

import com.book.mark.model.DataRequestPayload;
import com.book.mark.model.ExternalIdStatus;
import com.book.mark.model.RespPayload;

public interface CreateBookmarksService {

	public void readPayloadGenPdfBookmark(DataRequestPayload dataRequestPayload);
	public String genBookmarks(String submission_id,String temp);
	public ResponseEntity<RespPayload> genCsvByExternalId(String externalId);
	public void genSubmissionId();
	public ResponseEntity<ExternalIdStatus> genStateByExternalId(String ext_id);
	public ResponseEntity<RespPayload> getSubmisIdGenBookmark(String extId, String todo_item);
	
}
