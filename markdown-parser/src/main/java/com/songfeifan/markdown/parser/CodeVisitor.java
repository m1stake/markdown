package com.songfeifan.markdown.parser;

import com.songfeifan.markdown.component.CodeBlock;
import com.songfeifan.markdown.model.Line;

import java.util.ArrayList;
import java.util.List;

public class CodeVisitor implements Visitor {

    @Override
    public int visit(BlockTree node, CharSequence doc, int position) {
        Line line = nextLine(doc, position);
        String lineContent = line.getContent();
        if (lineContent != null && start(node, doc, position, lineContent) >= 0) {
            position = position + line.getLength();

            CodeBlock codeBlock = new CodeBlock();
            node.setComponent(codeBlock);

            codeBlock.setLanguage(parseLanguage(lineContent));

            List<String> codeLines = new ArrayList<>();
            line = nextLine(doc, position);
            lineContent = line.getContent();
            while (lineContent != null && end(node, doc, position, lineContent) < 0) {
                position = position + line.getLength();

                codeLines.add(lineContent);

                line = nextLine(doc, position);
                lineContent = line.getContent();
            }
            // code end line
            if (lineContent != null) {
                position = position + line.getLength();
            }
            codeBlock.setCode(String.join("\r\n", codeLines));
        }
        return position;
    }

    @Override
    public int start(BlockTree node, CharSequence doc, int position, String line) {
        if (line.trim().startsWith("```")) {
            return position;
        }
        return -1;
    }

    @Override
    public int end(BlockTree node, CharSequence doc, int position, String line) {
        if (line.trim().startsWith("```")) {
            return position;
        }
        return -1;
    }

    /**
     * 代码语言类型
     * @param line 代码块声明行
     * @return 语言类型
     */
    private String parseLanguage(String line) {
        return line.replace("```", "").trim().toLowerCase();
    }
}
