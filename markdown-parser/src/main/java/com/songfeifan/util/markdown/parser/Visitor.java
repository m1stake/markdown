package com.songfeifan.util.markdown.parser;

import com.songfeifan.util.markdown.model.Line;
import com.songfeifan.util.markdown.util.StringUtil;

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
