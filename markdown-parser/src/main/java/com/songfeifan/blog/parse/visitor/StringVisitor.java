package com.songfeifan.blog.parse.visitor;

import com.songfeifan.blog.parse.BlockTree;
import com.songfeifan.blog.parse.model.Line;
import com.songfeifan.blog.parse.component.StringBlock;

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
