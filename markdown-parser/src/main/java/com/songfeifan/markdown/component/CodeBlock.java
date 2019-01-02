package com.songfeifan.markdown.component;

import lombok.Data;

@Data
public class CodeBlock implements Block {

    private String language;

    private String code;

}
