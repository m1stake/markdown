package com.songfeifan.util.markdown.analysis;

import com.songfeifan.util.markdown.painter.Document;
import com.songfeifan.util.markdown.parser.BlockTree;

public interface Analyzer<T extends Document> {

    T analysis(BlockTree blockTree);

}
