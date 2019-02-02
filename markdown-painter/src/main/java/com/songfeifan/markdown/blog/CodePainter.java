package com.songfeifan.markdown.blog;

import com.songfeifan.markdown.blog.BlogDocument;
import com.songfeifan.markdown.component.CodeBlock;
import com.songfeifan.markdown.painter.BlockPainter;
import com.songfeifan.markdown.painter.CodeExtensionPainter;
import com.songfeifan.markdown.painter.Document;

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
