package com.songfeifan.markdown.component;

import lombok.Data;

@Data
public class HTMLBlock implements InLineBlock {
    private String content;
}
