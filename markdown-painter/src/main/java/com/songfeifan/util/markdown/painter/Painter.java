package com.songfeifan.util.markdown.painter;

public interface Painter<T extends Document> {

    String paint(T document);

}
