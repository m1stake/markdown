package com.songfeifan.blog.analyzer;


import com.songfeifan.blog.parse.BlockTree;

public interface Analyzer<T extends Document> {

    T analysis(BlockTree blockTree);

}
