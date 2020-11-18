package com.book.mark.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.book.mark.model.DataRequestPayload;
import com.book.mark.model.ErrMsg;
import com.book.mark.model.ExternalIdStatus;
import com.book.mark.model.SubmissionId;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnMarshaller {
	
	Logger logger = LoggerFactory.getLogger(UnMarshaller.class);

	public ExternalIdStatus unMarshallExternalIdResponse(String response) {
		ObjectMapper objectMapper = null;
		try {
			objectMapper = new ObjectMapper();
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			return objectMapper.readValue(response, ExternalIdStatus.class);
		} catch (Exception e) {
			logger.error("Exception occurred in unMarshallExternalIdResponse() ", e);
		}
		return null;
	}
	
	public SubmissionId unMarshallSubmissionId(String response) {
		ObjectMapper objectMapper = null;
		try {
			objectMapper = new ObjectMapper();
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			return objectMapper.readValue(response, SubmissionId.class);
		} catch (Exception e) {
			logger.error("Exception occurred in unMarshallSubmissionId() ", e);
		}
		return null;
	}
	
	public ErrMsg unMarshallErrMessage(String response) {
		ObjectMapper objectMapper = null;
		try {
			objectMapper = new ObjectMapper();
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			return objectMapper.readValue(response, ErrMsg.class);
		} catch (Exception e) {
			logger.error("Exception occurred in unMarshallSubmissionId() ", e);
		}
		return null;
	}
	
	public DataRequestPayload unmarshall(String response) {
		ObjectMapper objectMapper = null;
		try {
			objectMapper = new ObjectMapper();
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			return objectMapper.readValue(response, DataRequestPayload.class);
		} catch (Exception e) {
			logger.error("Exception occurred in unmarshall() ", e);
		}
		return null;
	}
	
}
