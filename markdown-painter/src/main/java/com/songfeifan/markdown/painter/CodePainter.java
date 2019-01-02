package com.songfeifan.markdown.painter;

import com.songfeifan.markdown.component.CodeBlock;

import java.util.HashMap;
import java.util.Map;

public class CodePainter implements BlockPainter<CodeBlock> {

    private Map<String, CodeExtensionPainter> extensionPainters = new HashMap<>();

    @Override
    public String paint(int index, CodeBlock component) {

        String language = component.getLanguage() == null ? "" : component.getLanguage();
        CodeExtensionPainter extensionPainter = extensionPainters.get(language);
        if (extensionPainter != null) {
            return extensionPainter.paint(index, component);
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
