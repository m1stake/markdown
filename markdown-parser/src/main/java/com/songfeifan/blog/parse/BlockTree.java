package com.songfeifan.blog.parse;

import com.songfeifan.blog.parse.component.Component;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BlockTree {

    private BlockTree prev;

    private BlockTree next;

    private BlockTree parent;

    private List<BlockTree> children = new ArrayList<>();

    private Component component;

    @Override
    public String toString() {
        return "BlockTree{" +
                "prev=" + ((prev == null) ? "null" : prev.component) +
                ", next=" + ((next == null) ? "null" : next.component) +
                ", parent=" + ((parent == null) ? "null" : parent.component) +
                ", children.size=" + ((children == null) ? "null" : children.size()) +
                ", component=" + component +
                '}';
    }
}
