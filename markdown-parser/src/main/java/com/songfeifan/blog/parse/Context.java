package com.songfeifan.blog.parse;

import com.songfeifan.blog.parse.visitor.Visitor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Context {

    public static List<Visitor> blockVisitors = new ArrayList<>();

    public static List<Visitor> inLineBlockVisitors = new ArrayList<>();

}
