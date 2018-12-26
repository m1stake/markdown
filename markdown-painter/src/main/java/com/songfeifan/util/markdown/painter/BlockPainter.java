package com.songfeifan.util.markdown.painter;

import com.songfeifan.util.markdown.component.Component;

public interface BlockPainter<T extends Component> {

    String paint(int index, T component);

}
