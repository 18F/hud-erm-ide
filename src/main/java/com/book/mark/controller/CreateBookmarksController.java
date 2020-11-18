package com.book.mark.controller;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.mark.model.DataRequestPayload;
import com.book.mark.model.ExternalIdStatus;
import com.book.mark.model.RespPayload;
import com.book.mark.service.CreateBookmarksService;

@RestController
@RequestMapping(value = "/bookmark")
public class CreateBookmarksController {

	@Autowired
	private CreateBookmarksService createBookmarksService;
	
	/*@PostMapping(value = "/readPayload")
	public void getPayload(@RequestBody DataRequestPayload dataRequestPayload) {
		createBookmarksService.readPayload(dataRequestPayload);
	}
	
	@GetMapping(value = "/genBookmark")
	public void genBookmark(@RequestParam("submissionId") String submission_id) {
		createBookmarksService.genBookmarks(submission_id);
	}*/
	
	@GetMapping(value = "/genBookmarks/{submissionId}")
	public String genBookmarks(@PathVariable("submissionId") String submission_id) {
		return createBookmarksService.genBookmarks(submission_id,"bookMarkPdf");
	}
	
	@PostMapping(value = "/getSubmiId")
	public void getSubmissionId() {
		createBookmarksService.genSubmissionId();
	}
	
	@GetMapping(value = "/getStatus/{externalId}")
	public ResponseEntity<ExternalIdStatus> genStateByExternalId(@PathVariable("externalId") String ext_id) {
		ResponseEntity<ExternalIdStatus> respEnt = createBookmarksService.genStateByExternalId(ext_id);
		return respEnt;
	}
	
	/*@GetMapping(value = "/getSubmiIdGenBookmark/{externalId}")
	public ResponseEntity<RespPayload> getSubmisIdGenBookmark(@PathVariable("externalId") String externalId) {
		if(StringUtils.isEmpty(externalId) || externalId.trim().equals("")) {
			RespPayload respPayload = new RespPayload();
			respPayload.setMessage("External Id is required");
			return new ResponseEntity<RespPayload>(respPayload, HttpStatus.BAD_REQUEST);
		}
		ResponseEntity<RespPayload> respEnt = createBookmarksService.getSubmisIdGenBookmark(externalId);
		return respEnt;
	}*/
	
	@GetMapping(value = "/genCsv")
	public ResponseEntity<RespPayload> genCsvByExternalId(@QueryParam("externalId") String externalId) {
		if(StringUtils.isEmpty(externalId) || externalId.trim().equals("")) {
			RespPayload respPayload = new RespPayload();
			respPayload.setMessage("External Id is required");
			return new ResponseEntity<RespPayload>(respPayload, HttpStatus.BAD_REQUEST);
		}
		return createBookmarksService.genCsvByExternalId(externalId);
	}
	
	@GetMapping(value = "/getSubmiIdGenBookmark")
	public ResponseEntity<RespPayload> getSubmisIdGenBookmark1(@QueryParam("externalId") String externalId) {
		if(StringUtils.isEmpty(externalId) || externalId.trim().equals("")) {
			RespPayload respPayload = new RespPayload();
			respPayload.setMessage("External Id is required");
			return new ResponseEntity<RespPayload>(respPayload, HttpStatus.BAD_REQUEST);
		}
		ResponseEntity<RespPayload> respEnt = createBookmarksService.getSubmisIdGenBookmark(externalId,"bookMarkPdf");
		return respEnt;
	}
	
	@GetMapping(value = "/getSubmiIdGenCsv")
	public ResponseEntity<RespPayload> getSubmisIdGenCsv(@QueryParam("externalId") String externalId) {
		if(StringUtils.isEmpty(externalId) || externalId.trim().equals("")) {
			RespPayload respPayload = new RespPayload();
			respPayload.setMessage("External Id is required");
			return new ResponseEntity<RespPayload>(respPayload, HttpStatus.BAD_REQUEST);
		}
		ResponseEntity<RespPayload> respEnt = createBookmarksService.getSubmisIdGenBookmark(externalId,"GenCSV");
		return respEnt;
	}
	
	@GetMapping(value = "/genPdfGenCsv")
	public ResponseEntity<RespPayload> getSubmisIdGenPdfGenCsv(@QueryParam("externalId") String externalId) {
		if(StringUtils.isEmpty(externalId) || externalId.trim().equals("")) {
			RespPayload respPayload = new RespPayload();
			respPayload.setMessage("External Id is required");
			return new ResponseEntity<RespPayload>(respPayload, HttpStatus.BAD_REQUEST);
		}
		ResponseEntity<RespPayload> respEnt = createBookmarksService.getSubmisIdGenBookmark(externalId,"Both");
		return respEnt;
	}
	
}
