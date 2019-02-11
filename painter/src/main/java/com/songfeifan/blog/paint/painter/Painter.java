package com.songfeifan.blog.paint.painter;

import com.songfeifan.blog.analyzer.Document;

public interface Painter<T extends Document> {

    String paint(T document);

}
