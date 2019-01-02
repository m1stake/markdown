package com.songfeifan.markdown.blog.exception;

public class DocumentFormatException extends RuntimeException {

    public DocumentFormatException(String msg, Exception e) {
        super(msg, e);
    }

    public DocumentFormatException(String msg) {
        super(msg);
    }

}
