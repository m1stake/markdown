package com.songfeifan.util.markdown.parser;

import com.songfeifan.util.markdown.component.Header;
import com.songfeifan.util.markdown.component.StringBlock;
import com.songfeifan.util.markdown.model.Line;

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
