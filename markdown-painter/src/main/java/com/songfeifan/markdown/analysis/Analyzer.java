package com.songfeifan.markdown.analysis;

import com.songfeifan.markdown.painter.Document;
import com.songfeifan.markdown.parser.BlockTree;

public interface Analyzer<T extends Document> {

    T analysis(BlockTree blockTree);

}
