package com.book.mark.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitWidthDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.book.mark.constants.PdfBookmarkConstants;
import com.book.mark.model.DataRequestPayload;
import com.book.mark.model.DocumentFields;
import com.book.mark.model.Documents;
import com.book.mark.model.ErrMsg;
import com.book.mark.model.ExternalIdStatus;
import com.book.mark.model.Pages;
import com.book.mark.model.RespPayload;
import com.book.mark.model.SubmissionId;
import com.book.mark.model.Transcription;
import com.book.mark.service.CreateBookmarksService;
import com.book.mark.util.UnMarshaller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CreateBookmarksServiceImpl implements CreateBookmarksService,PdfBookmarkConstants{
	
	Logger logger = LoggerFactory.getLogger(CreateBookmarksServiceImpl.class);

	public void readPayloadGenPdfBookmark(DataRequestPayload dataRequestPayload) {
		try {
			logger.info("JSON Parsing Started");
			//System.out.println("JSON Parsing Started");
			String build_file_path = "";
			build_file_path += url_const;
			
			/*List<SubmissionFiles> submission_files = dataRequestPayload.getSubmission_files();
			if(submission_files != null && submission_files.size()>0) {build_file_path += submission_files.get(0).getUrl();}
			logger.info("Path to download URL "+build_file_path);*/
			//for(SubmissionFiles sf : submission_files) {System.out.println(sf.toString());}
			
			LinkedHashMap<String, List<String>> orderedMap = new LinkedHashMap<String, List<String>>();
			List<Documents> documents = dataRequestPayload.getDocuments();
			for(Documents docs : documents) {
				String layout_name = docs.getLayout_name();
				//ArrayList<String> layout_list = new ArrayList<String>();
				ArrayList<String> pages_list = new ArrayList<String>();
				List<Pages> pages = docs.getPages();
				for(Pages p : pages) {
					pages_list.add(String.valueOf(p.getFile_page_number())+"-"+String.valueOf(p.getLayout_page_number()));
					//layout_list.add(String.valueOf(p.getLayout_page_number()));
				}
				orderedMap.put(layout_name, pages_list);
			}
			logger.info("Required Mapping List - ",orderedMap);
			
			/*String dummy_url1="https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
			//String dummy_url2="https://www.learningcontainer.com/wp-content/uploads/2019/09/sample-pdf-file.pdf";
			InputStream in = new URL(dummy_url1).openStream();
			//InputStream in = new URL(build_file_path).openStream();
			Files.copy(in, Paths.get(writePdfFromUrl), StandardCopyOption.REPLACE_EXISTING);*/

			logger.info("Read data for the pdf without bookmarks started at location "+MergedDocumentPath);
			PDDocument writeDoc = new PDDocument();
			PDDocument readDoc = PDDocument.load(new File(MergedDocumentPath));
			int noOfPages = readDoc.getNumberOfPages();
			logger.info("No of Pages observed in the pdf are - "+noOfPages);
			
			PDDocumentOutline outline = new PDDocumentOutline();
			writeDoc.getDocumentCatalog().setDocumentOutline(outline);
			PDOutlineItem pagesOutline = new PDOutlineItem();
			pagesOutline.setTitle("All Pages");
			outline.addLast(pagesOutline);
			
			for(Map.Entry<String, List<String>> entry: orderedMap.entrySet()) {
				String title = entry.getKey();
				List<String> page_list = entry.getValue();
				
				PDOutlineItem bookmark = new PDOutlineItem();
				bookmark.setTitle(title);
				PDPageDestination dest = new PDPageFitWidthDestination();
				boolean flag = true;
				
				List<String> pagesList = new ArrayList<String>();
				List<String> layoutList = new ArrayList<String>();
				
				for(int index=0;index<page_list.size();index++) {
					String[] splitData = page_list.get(index).split("-");
					pagesList.add(String.valueOf(splitData[0]));//FILE_PAGE_NUMBER
					layoutList.add(String.valueOf(splitData[1]));//LAYOUT_PAGE_NUMBER
				}
				
				int pageNum = 1;//INDEX FOR ADDING PDF
				int layoutIndex = 0;//INDEX FOR LAYOUT
				for(PDPage page : readDoc.getPages()) {
					
					if(pagesList.contains(String.valueOf(pageNum))) {
						if(flag) {
							//dest.setPage(page);
							//bookmark.setDestination(dest);
							PDPageDestination dest2 = new PDPageFitWidthDestination();
							dest2.setPage(page);
							PDOutlineItem subbookmark1 = new PDOutlineItem();
							subbookmark1.setDestination(dest2);
							//subbookmark1.setTitle(String.valueOf(pageNum));
							subbookmark1.setTitle(layoutList.get(layoutIndex));
							bookmark.addLast(subbookmark1);
							
							flag = false;
							writeDoc.addPage(page);
						}else {
							//dest.setPage(page);
							PDPageDestination dest2 = new PDPageFitWidthDestination();
							dest2.setPage(page);
							PDOutlineItem subbookmark1 = new PDOutlineItem();
							subbookmark1.setDestination(dest2);
							//subbookmark1.setTitle(String.valueOf(pageNum));
							subbookmark1.setTitle(layoutList.get(layoutIndex));
							bookmark.addLast(subbookmark1);
							writeDoc.addPage(page);
						}
						layoutIndex++;
					}
					pageNum++;
				}
				bookmark.openNode();
				pagesOutline.addLast(bookmark);
				logger.info("Bookmark added for ["+title+"] with page id's "+page_list);
			}
			
			pagesOutline.openNode();
			outline.openNode();
			
			String writeToPath = generateFileName(MergedDocumentPath);

			writeDoc.save(new File(writeToPath));
			logger.info("Write Data to the pdf with bookmarks completed at location "+writeToPath);
			//System.out.println("Write Data to the pdf with bookmarks completed at location "+writePdfPathNew);
			writeDoc.close();
			readDoc.close();
			
			logger.info("JSON Parsing Finished");
		}catch(Exception e) {
			logger.error("Exception in readPayload() ", e);
		}		
	}
	
	public void readPayloadGenCSV(DataRequestPayload dataRequestPayload) {
		try {
			logger.info("JSON Parsing Started");
			String extId = dataRequestPayload.getExternal_id();
			String modifiedCsvFileName = generateCSVFileName(genCsvFilePath, extId);
			logger.info(modifiedCsvFileName);
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(modifiedCsvFileName)));
			List<Documents> documents = dataRequestPayload.getDocuments();
			for(Documents docs : documents) {
				String layout_name = docs.getLayout_name();
				List<DocumentFields> documentFields = docs.getDocument_fields();
				bw.write("LayoutName,"+layout_name+NEWLINE);
				for(DocumentFields docField : documentFields) {
					String name = docField.getName();
					Transcription transcription = docField.getTranscription();
					String normalized = transcription.getNormalized();
					//bw.write((name != null ? name : "")+","+(normalized != null ? normalized : "")+NEWLINE);
					bw.write(name+","+normalized+NEWLINE);
				}
				bw.write(NEWLINE);
			}
			bw.close();						
			logger.info("JSON Parsing Finished");
		}catch(Exception e) {
			logger.error("Exception in readPayload() ", e);
		}		
	}
	
	public String genBookmarks(String submission_id, String todoItem) {
		boolean flag = false;
		try {
			//39 VALUE MAKE IT AS PARAM(submission_id)
			//String url="http://13.72.109.50/api/v5/submissions/39?flat=false";
			if(submission_id != null && !submission_id.equals("") && !submission_id.equalsIgnoreCase("null")) {
				String build_url = hostName_genSubId+submission_id+urlAppender;
				logger.info("Building url is done for given submissionId as "+build_url);

				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", "Token bca5a45db3ad094449bb6569a69f705ba2a8a5c3");
				//headers.set("Authorization", "Token 984910a508f30eb63dfe404aa8e2d4acf505ae14");

				HttpEntity<String> httpReqEntity = new HttpEntity<String>(headers);
				RestTemplate restTemplate = new RestTemplate();

				/*if(todoItem.equalsIgnoreCase(generateCSV) || todoItem.equalsIgnoreCase(genPdfgenCsv))
					TimeUnit.SECONDS.sleep(90);*/
				ResponseEntity<String> response = restTemplate.exchange(build_url, HttpMethod.GET, httpReqEntity, String.class);
				if(response != null) {
					logger.info("Parsing the response recieved from "+build_url);
					UnMarshaller unMarshaller = new UnMarshaller();
					DataRequestPayload dataRequestPayload = unMarshaller.unmarshall(response.getBody());

					/*String build_file_path = "http://13.72.109.50";
					List<SubmissionFiles> submission_files = dataRequestPayload.getSubmission_files();
					if(submission_files != null && submission_files.size()>0) {
						build_file_path += submission_files.get(0).getUrl();
					}
					System.out.println("Path to download URL "+build_file_path);*/
					
					if(dataRequestPayload != null) {
						if(todoItem != null && todoItem.equalsIgnoreCase(bookMarkPdf)) {
							readPayloadGenPdfBookmark(dataRequestPayload);
						}else if(todoItem != null && todoItem.equalsIgnoreCase(generateCSV)){
							readPayloadGenCSV(dataRequestPayload);
						}else if(todoItem != null && todoItem.equalsIgnoreCase(genPdfgenCsv)){
							readPayloadGenPdfBookmark(dataRequestPayload);
							readPayloadGenCSV(dataRequestPayload);
						}
						flag = true;//NEED THIS FLAG TO RETURN SUCCESS OR FAILURE MESSAGE
					}else {
						logger.error("Expected JSON Object from response is null, cannot proceed further.");
					}
				}else {
					logger.error("Response from "+build_url+" is null, cannot proceed further.");
				}
			}else {
				logger.error("Submission Id is null, cannot proceed further.");
			}
		}catch(Exception e) {
			logger.error("Exception", e);
		}
		if(flag)
			return success_message;
		else
			return "Issue Occurred while Generating Bookmark";
	}
	
	private String generateFileName(String fileReadLocation) {
		fileReadLocation = fileReadLocation.replace("MergeMultipleDocs", "MergedDoc");
		String pattern = "MMddyyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		
		String fileName = new File(fileReadLocation).getName();
		String[] name = fileName.split("\\.");
		//String build_fileName = name[0].concat("_"+date.toString());
		String build_fileName = name[0].concat("_Bookmarked");
		String newPath = fileReadLocation.replace(fileName, "");
		return newPath+build_fileName+".pdf";
	}
	
	private String generateCSVFileName(String fileReadLocation,String extId) {
		/*String pattern = "MMddyyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());*/
		
		String fileName = new File(fileReadLocation).getName();
		String[] name = fileName.split("\\.");
		//String build_fileName = name[0].concat("_"+date.toString());
		String build_fileName = name[0].concat("_"+extId);
		String newPath = fileReadLocation.replace(fileName, "");
		return newPath+build_fileName+".csv";
	}
	
	public void genSubmissionId() {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			//headers.set("Authorization", "Token 6473f7df6f524232e012c96c74217f37872426c4");
			headers.set("Authorization", "Token bca5a45db3ad094449bb6569a69f705ba2a8a5c3");
			logger.info("HttpHeader values added...");
			
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			List<Resource> resourceList = getUserFileResourceList(s3_bucket_pdf_files_location);
			for(Resource resource : resourceList) {
				body.add("file", resource);
			}
			logger.info("Files dynamically added from location - "+s3_bucket_pdf_files_location);
			
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			RestTemplate restTemplate = new RestTemplate();
			logger.info("Rest API Call Started...");
			ResponseEntity<String> response = restTemplate.postForEntity(hostName, requestEntity, String.class);
			logger.info("Rest API Call Finished...");
			if(response != null) {
				if(response.getBody() != null) {
					UnMarshaller unMarshaller = new UnMarshaller();
					SubmissionId submissionId = unMarshaller.unMarshallSubmissionId(response.getBody());
					logger.info("Generated Submission ID value is "+submissionId.getSubmissionId());					
					//genBookmarks(submissionId.getSubmissionId());
				} else {
					logger.error("API Response BODY from URL - "+hostName+" is null, cannot proceed further.");
				}
			} else {
				logger.error("API Response from URL - "+hostName+" is null, cannot proceed further.");
			}
		}catch(Exception e) {
			logger.error("Exception", e);
		}
	}
	
	public ResponseEntity<RespPayload> genCsvByExternalId(String extId){
		boolean isExecutionDone = false;
		RespPayload respPayload = new RespPayload();
		try {
			ResponseEntity<ExternalIdStatus> respObj = genStateByExternalId(extId);
			if(respObj != null) {
				ExternalIdStatus extObj = (ExternalIdStatus)respObj.getBody();
				if(extObj == null || extObj.getState() == null) {
					respPayload.setMessage("External Id Not Found");
					return new ResponseEntity<RespPayload>(respPayload, HttpStatus.OK);
				}
				if(extObj != null) {
					if(extObj.getState() != null && !extObj.getState().equalsIgnoreCase(COMPLETE)) {
						respPayload.setMessage("Submission State is not Complete but found "+extObj.getState());
						return new ResponseEntity<RespPayload>(respPayload, HttpStatus.OK);
					}
					genBookmarks(String.valueOf(extObj.getId()), generateCSV);
					isExecutionDone = true;
				}
			}
			if(isExecutionDone)
				respPayload.setMessage("Success");
			else
				respPayload.setMessage("Failed");
		}catch(Exception e) {
			logger.error("Exception occurred while genCsvByExternalId: ",e);
		}
		return new ResponseEntity<RespPayload>(respPayload, HttpStatus.OK);
	}
	
	public ResponseEntity<ExternalIdStatus> genStateByExternalId(String ext_id) {
		ExternalIdStatus extObj = new ExternalIdStatus();
		boolean isErrorOccurred = false;
		try {
			String build_url = hostName_external+ext_id;
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Token bca5a45db3ad094449bb6569a69f705ba2a8a5c3");
			logger.info("HttpHeader values added...");
			
			Map<String, String> body = new HashMap<String, String>();
			body.put("Authorization", "Token bca5a45db3ad094449bb6569a69f705ba2a8a5c3");
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<?> response = restTemplate.exchange(build_url, HttpMethod.GET, requestEntity, String.class);
			if(response != null) {
				String respBody = (String)response.getBody();
				if(respBody != null) {
					UnMarshaller unMarshaller = new UnMarshaller();
					extObj = unMarshaller.unMarshallExternalIdResponse(respBody);
					if(extObj != null)
						logger.info(extObj.toString());
					else {
						logger.error("unMarshallExternalIdResponse is null, cannot proceed further.");
					}
				}else {
					logger.error("API Response BODY from URL - "+build_url+" is null, cannot proceed further.");
				}
			}else {
				logger.error("API Response from URL - "+build_url+" is null, cannot proceed further.");
			}
		}catch(Exception e) {
			isErrorOccurred = true;
			logger.error("Exception occurred in genStateByExternalId() ", e);
		}
		if(isErrorOccurred)
			return new ResponseEntity<ExternalIdStatus>(extObj, HttpStatus.OK);
		else
			return new ResponseEntity<ExternalIdStatus>(extObj, HttpStatus.OK);
	}
	
	public static List<Resource> getUserFileResourceList(String location) throws IOException {
		List<Resource> resourceList = new ArrayList<Resource>();
		File file = new File(location);
        String[] fileList = file.list();
        for(String name : fileList) {
        	System.out.println(name);
        	java.io.File file1 = new java.io.File(location+name);
        	resourceList.add(new FileSystemResource(file1));
        }
        return resourceList;
	}
	
	@SuppressWarnings("deprecation")
	public ResponseEntity<RespPayload> getSubmisIdGenBookmark(String extId,String todoItem){
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.set("Authorization", "Token 984910a508f30eb63dfe404aa8e2d4acf505ae14");
			//headers.set("externalId", extId);
			logger.info("HttpHeader values added...");
			
			PDFMergerUtility PDFMerger = new PDFMergerUtility();
			PDFMerger.setDestinationFileName(MergedDocumentPath);
			File file = new File(s3_bucket_pdf_files_location);
	        String[] fileList = file.list();
	        for(String name : fileList) {
	        	java.io.File file1 = new java.io.File(s3_bucket_pdf_files_location+name);
	        	PDDocument doc = PDDocument.load(file1);
	        	PDFMerger.addSource(file1);
	        	doc.close();
	        }
	        PDFMerger.mergeDocuments();
	        
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			//List<Resource> resourceList = getUserFileResourceList(s3_bucket_pdf_files_location);
			/*List<Resource> resourceList = getUserFileResourceList(mergedContentPdf);
			for(Resource resource : resourceList) {
				body.add("file", resource);
			}*/
			//logger.info("Files dynamically added from location - "+s3_bucket_pdf_files_location);
			body.add("file", new FileSystemResource(new java.io.File(MergedDocumentPath)));
			body.add("external_id", extId);
			logger.info("File added from location - "+MergedDocumentPath);
			
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
			RestTemplate restTemplate = new RestTemplate();
			logger.info("Rest API Call Started...");
			ResponseEntity<String> response = null;
			try {
				response = restTemplate.postForEntity(hostName+"?flat=false", requestEntity, String.class);
			}catch(HttpClientErrorException hceex) {
				logger.error(hceex.getMessage());
				if(hceex.getMessage() != null) {
					String message = hceex.getMessage().replace("409 Conflict:","").replace("[", "").replace("]", "").trim();
					//UnMarshaller unMarshaller = new UnMarshaller();
					//ErrMsg errMsg=unMarshaller.unMarshallErrMessage(message);
					RespPayload respPayload = new RespPayload();
					respPayload.setMessage(message);
					return new ResponseEntity<RespPayload>(respPayload, HttpStatus.CONFLICT);
				}
			}catch(Exception ex) {
				logger.error(ex.getMessage());
			}
			//String response1 =  restTemplate.postForObject(hostName, requestEntity, String.class);
			logger.info("Rest API Call Finished...");
			if(response != null) {
				if(response.getBody() != null) {
					UnMarshaller unMarshaller = new UnMarshaller();
					SubmissionId submissionId = unMarshaller.unMarshallSubmissionId(response.getBody());
					logger.info("Generated Submission ID value is "+submissionId.getSubmissionId());
					TimeUnit.SECONDS.sleep(20);
					genBookmarks(submissionId.getSubmissionId(),todoItem);
				} else {
					logger.error("API Response BODY from URL - "+hostName+" is null, cannot proceed further.");
				}
			} else {
				logger.error("API Response from URL - "+hostName+" is null, cannot proceed further.");
			}
			RespPayload respPayload = new RespPayload();
			respPayload.setMessage("Success");
			return new ResponseEntity<RespPayload>(respPayload, HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Exception", e);
			RespPayload respPayload = new RespPayload();
			respPayload.setMessage("Fail");
			return new ResponseEntity<RespPayload>(respPayload, HttpStatus.OK);
		}
	}

}