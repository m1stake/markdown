package com.songfeifan.blog.analyzer.exception;

public class DocumentFormatException extends RuntimeException {

    public DocumentFormatException(String msg, Exception e) {
        super(msg, e);
    }

    public DocumentFormatException(String msg) {
        super(msg);
    }

}
