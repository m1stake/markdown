package com.songfeifan.util.markdown.parser;

public class Parser {

    public BlockTree parse(CharSequence charSequence) {
        BlockTree root = new BlockTree();
        BlockTree node = root;
        int position = 0;
        while (position < charSequence.length()) {
            for (Visitor visitor : Context.blockVisitors) {
                int rawPosition = position;
                position = visitor.visit(node, charSequence, position);
                // if find match, next loop
                if (rawPosition < position) {
                    BlockTree prev = node;
                    node = new BlockTree();
                    prev.setNext(node);
                    node.setPrev(prev);
                    break;
                }
            }
        }
        return root;
    }

}
