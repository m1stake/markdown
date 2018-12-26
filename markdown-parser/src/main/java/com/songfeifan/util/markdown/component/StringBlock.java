package com.songfeifan.util.markdown.component;

import com.songfeifan.util.markdown.parser.Context;
import lombok.Data;

@Data
public class StringBlock implements InLineBlock, Block {

    private String content;

    public StringBlock(String content) {
        this.content = content;
    }

    public StringBlock() {
    }

    @Override
    public String toString() {
        return content;
    }
}
