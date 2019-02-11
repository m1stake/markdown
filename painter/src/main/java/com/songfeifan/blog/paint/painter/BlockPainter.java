package com.songfeifan.blog.paint.painter;


import com.songfeifan.blog.analyzer.Document;
import com.songfeifan.blog.parse.component.Component;

public interface BlockPainter<T extends Component> {

    String paint(int index, T component, Document document);

}
