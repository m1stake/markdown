package com.songfeifan.markdown.component;

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
