package com.songfeifan.markdown.painter;

public interface Painter<T extends Document> {

    String paint(T document);

}
