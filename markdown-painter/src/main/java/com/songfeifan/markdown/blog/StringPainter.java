package com.songfeifan.markdown.blog;

import com.songfeifan.markdown.component.StringBlock;
import com.songfeifan.markdown.painter.BlockPainter;
import com.songfeifan.markdown.painter.Document;

public class StringPainter implements BlockPainter<StringBlock> {

    @Override
    public String paint(int index, StringBlock component, Document document) {
        return String.format("<p key='%d'>%s</p>", index, component.getContent());
    }

}
