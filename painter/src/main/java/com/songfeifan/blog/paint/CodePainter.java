package com.songfeifan.blog.paint;

import com.songfeifan.blog.analyzer.Document;
import com.songfeifan.blog.paint.painter.BlockPainter;
import com.songfeifan.blog.paint.painter.CodeExtensionPainter;
import com.songfeifan.blog.parse.component.CodeBlock;

import java.util.HashMap;
import java.util.Map;

public class CodePainter implements BlockPainter<CodeBlock> {

    private Map<String, CodeExtensionPainter> extensionPainters = new HashMap<>();

    @Override
    public String paint(int index, CodeBlock component, Document document) {

        String language = component.getLanguage() == null ? "" : component.getLanguage();
        CodeExtensionPainter extensionPainter = extensionPainters.get(language);
        if (extensionPainter != null) {
            return extensionPainter.paint(index, component, document);
        }

        return String.format(
                "\r<pre key='%d'>\r\n" +
                        "  <code className='%s' style={codeStyle}>\r\n" +
                        "    {`%s`}\r\n" +
                        "  </code>\r\n" +
                        "</pre>",
                index,
                language,
                component.getCode());
    }

    public void putExtension(String language, CodeExtensionPainter extensionPainter) {
        extensionPainters.put(language, extensionPainter);
    }
}
