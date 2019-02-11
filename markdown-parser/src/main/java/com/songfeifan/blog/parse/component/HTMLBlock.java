package com.songfeifan.blog.parse.component;

import lombok.Data;

@Data
public class HTMLBlock implements InLineBlock {
    private String content;
}
