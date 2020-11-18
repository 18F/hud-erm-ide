package com.book.mark.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transcription {
	private String raw;
	private String normalized;
	private String source;
	private boolean data_deleted;
	private String user_transcribed;
	public String getRaw() {
		return raw;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
	public String getNormalized() {
		return normalized;
	}
	public void setNormalized(String normalized) {
		this.normalized = normalized;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public boolean isData_deleted() {
		return data_deleted;
	}
	public void setData_deleted(boolean data_deleted) {
		this.data_deleted = data_deleted;
	}
	public String getUser_transcribed() {
		return user_transcribed;
	}
	public void setUser_transcribed(String user_transcribed) {
		this.user_transcribed = user_transcribed;
	}
	
	
}
