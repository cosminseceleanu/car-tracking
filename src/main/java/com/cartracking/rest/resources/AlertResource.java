package com.cartracking.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

public class AlertResource extends ResourceSupport {

    private long rid;
    private String title;
    private String message;
    private Date date;

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
