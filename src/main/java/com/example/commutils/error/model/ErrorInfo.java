package com.example.commutils.error.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorInfo {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;
    private Integer code;
    private String message;
    private String url;
    private String[] data;
	private String[] detail;
    private String log;
    private Date date;
}