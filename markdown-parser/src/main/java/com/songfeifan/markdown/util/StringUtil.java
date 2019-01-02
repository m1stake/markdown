package com.songfeifan.markdown.util;

import com.songfeifan.markdown.model.Line;

public abstract class StringUtil {

    /**
     * next line of the char sequence begin with the position
     * @param charSequence char sequence
     * @param position current position
     * @return next line, empty string means empty line, NULL means end of sequence
     */
    public static Line nextLine(CharSequence charSequence, int position) {
        if (position == charSequence.length()) {
            return new Line();
        }

        StringBuilder line = new StringBuilder();
        int offset = 0;
        while ((position + offset) < charSequence.length()) {
            char c = charSequence.charAt(position + offset);
            offset++;
            if (c == '\n') {
                break;
            }
            if (c == '\r') {
                // has rest
                if((position + offset) < charSequence.length()) {
                    c = charSequence.charAt(position + offset);
                    if (c == '\n') {
                        offset++;
                    }
                }
                break;
            }
            line.append(c);
        }
        return new Line(line.toString(), offset);
    }

}
