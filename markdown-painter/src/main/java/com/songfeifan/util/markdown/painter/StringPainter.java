package com.songfeifan.util.markdown.painter;

import com.songfeifan.util.markdown.component.StringBlock;

public class StringPainter implements BlockPainter<StringBlock> {

    @Override
    public String paint(int index, StringBlock component) {
        return String.format("<p key='%d'>%s</p>", index, component.getContent());
    }

}
