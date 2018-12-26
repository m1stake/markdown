package com.songfeifan.util.markdown.painter;

import com.songfeifan.util.markdown.component.CodeBlock;

public class CodePainter implements BlockPainter<CodeBlock> {

    @Override
    public String paint(int index, CodeBlock component) {
        return String.format(
                "\r<pre key='%d'>\r\n" +
                        "  <code className='%s' style={codeStyle}>\r\n" +
                        "    {`%s`}\r\n" +
                        "  </code>\r\n" +
                        "</pre>",
                index,
                component.getLanguage() == null ? "" : component.getLanguage(),
                component.getCode());
    }

}
