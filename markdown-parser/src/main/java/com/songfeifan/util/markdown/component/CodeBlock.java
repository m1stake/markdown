package com.songfeifan.util.markdown.component;

import com.songfeifan.util.markdown.parser.Context;
import lombok.Data;

@Data
public class CodeBlock implements Block {

    private String language;

    private String code;

}
