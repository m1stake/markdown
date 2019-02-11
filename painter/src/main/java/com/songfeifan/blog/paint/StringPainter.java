package com.songfeifan.blog.paint;


import com.songfeifan.blog.analyzer.Document;
import com.songfeifan.blog.paint.painter.BlockPainter;
import com.songfeifan.blog.parse.component.StringBlock;

public class StringPainter implements BlockPainter<StringBlock> {

    @Override
    public String paint(int index, StringBlock component, Document document) {
        return String.format("<p key='%d'>%s</p>", index, component.getContent());
    }

}
