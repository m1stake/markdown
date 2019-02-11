package com.songfeifan.blog.parse.component;

import lombok.Data;

@Data
public class CodeBlock implements Block {

    private String language;

    private String code;

}
