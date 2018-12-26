package com.songfeifan.util.markdown.model;

import lombok.Data;

@Data
public class Line {

    private String content;

    private int length;

    public Line(String content, int length) {
        this.content = content;
        this.length = length;
    }

    public Line() {
    }
}
