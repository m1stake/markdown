package com.songfeifan.markdown.parser;

import com.songfeifan.markdown.component.StringBlock;
import com.songfeifan.markdown.model.Line;

public class StringVisitor implements Visitor {

    @Override
    public int visit(BlockTree node, CharSequence doc, int position) {
        Line line = nextLine(doc, position);
        String lineContent = line.getContent();
        if (lineContent != null) {
            position = position + line.getLength();
            node.setComponent(new StringBlock(lineContent));
        }
        return position;
    }

    @Override
    public int end(BlockTree node, CharSequence doc, int position, String line) {
        return position;
    }
}
