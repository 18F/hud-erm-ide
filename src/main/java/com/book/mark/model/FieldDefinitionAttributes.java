package com.book.mark.model;

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
public class FieldDefinitionAttributes {
	@JsonProperty("required")
	private boolean required;
	@JsonProperty("data_type")
	private String data_type;
	@JsonProperty("multiline")
	private boolean multiline;
	@JsonProperty("supervision_override")
	private String supervision_override;
	@JsonProperty("routing")
	private boolean routing;
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public boolean isMultiline() {
		return multiline;
	}
	public void setMultiline(boolean multiline) {
		this.multiline = multiline;
	}
	public String getSupervision_override() {
		return supervision_override;
	}
	public void setSupervision_override(String supervision_override) {
		this.supervision_override = supervision_override;
	}
	
	
}
