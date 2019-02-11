package com.songfeifan.blog.parse.component;

import lombok.Data;

@Data
public class Header implements Block {

    private int level;

    private Component content;

    public Header() { }

    public Header(int level, Component content) {
        this.level = level;
        this.content = content;
    }
}
