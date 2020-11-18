package com.book.mark.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalIdStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private int id;
	@JsonProperty("external_id")
	private String external_id;
	@JsonProperty("state")
	private String state;
	@JsonProperty("substate")
	private String substate;
	/*private List<Object> exceptions;
	private boolean halted;
	private String start_time;
	private String complete_time;
	private String supervision_url;
	private String metadata;
	private boolean data_deleted;*/
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
	/*public List<Object> getExceptions() {
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
	}*/
	
	@Override
	public String toString() {
		return "{id:"+this.id+",external_id:"+this.external_id+",state:"+this.state+",substate:"+this.substate+"}";
	}
	
}
