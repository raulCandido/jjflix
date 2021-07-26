package br.com.jjflix.exception;

import java.util.Date;
import java.util.List;

public class ErrorMessage {

    private Date timestamp;
    private Integer status;
    private String error;
    private List<String> messages;
    private List<String> fields;
    private String path;

    public ErrorMessage() {
    }

    public ErrorMessage(Date timestamp, Integer status, String error, List<String> messages, List<String> fields, String path) {
	super();
	this.timestamp = timestamp;
	this.status = status;
	this.error = error;
	this.messages = messages;
	this.fields = fields;
	this.path = path;
    }

    
    public List<String> getFields() {
        return fields;
    }

    public List<String> getMessages() {
        return messages;
    }

    public Date getTimestamp() {
	return timestamp;
    }

    public Integer getStatus() {
	return status;
    }

    public String getError() {
	return error;
    }

    public String getPath() {
	return path;
    }

}
