package com.songfeifan.markdown.painter;

import com.songfeifan.markdown.component.Component;

public interface BlockPainter<T extends Component> {

    String paint(int index, T component, Document document);

}
