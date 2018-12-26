package com.songfeifan.util.markdown.parser;

import com.songfeifan.util.markdown.component.Block;
import com.songfeifan.util.markdown.component.Component;
import com.songfeifan.util.markdown.component.InLineBlock;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Context {

    public static List<Visitor> blockVisitors = new ArrayList<>();

    public static List<Visitor> inLineBlockVisitors = new ArrayList<>();

}
