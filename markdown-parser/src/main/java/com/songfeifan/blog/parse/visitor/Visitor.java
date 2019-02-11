package com.songfeifan.blog.parse.visitor;

import com.songfeifan.blog.parse.BlockTree;
import com.songfeifan.blog.parse.model.Line;
import com.songfeifan.blog.parse.util.StringUtil;

public interface Visitor {

    default int visit(BlockTree node, CharSequence doc, int position) {
        return position;
    }

    default int start(BlockTree node, CharSequence doc, int position, String line) {
        return position;
    }

    default int end(BlockTree node, CharSequence doc, int position, String line) {
        return position;
    }

    default Line nextLine(CharSequence charSequence, int position) {
        return StringUtil.nextLine(charSequence, position);
    }

}
