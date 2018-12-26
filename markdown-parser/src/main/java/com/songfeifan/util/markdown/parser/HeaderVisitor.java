package com.songfeifan.util.markdown.parser;

import com.songfeifan.util.markdown.component.Header;
import com.songfeifan.util.markdown.component.StringBlock;
import com.songfeifan.util.markdown.model.Line;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeaderVisitor implements Visitor {

    private static final Pattern pattern = Pattern.compile("\\s*(#+)(.*)\\s*");

    @Override
    public int visit(BlockTree node, CharSequence doc, int position) {
        Line line = nextLine(doc, position);
        String lineContent = line.getContent();
        if (lineContent != null && start(node, doc, position, lineContent) >= 0) {
            position = position + line.getLength();
            Header header = parseHeader(lineContent);
            node.setComponent(header);
        }
        return position;
    }

    @Override
    public int start(BlockTree node, CharSequence doc, int position, String line) {
        if (line.trim().startsWith("#")) {
            return position;
        }
        return -1;
    }

    private Header parseHeader(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String sign = matcher.group(1);
            String content = matcher.group(2);
            return new Header(sign.length(), new StringBlock(content.trim()));
        }
        throw new RuntimeException("No Header pattern found from \"" + line + "\"");
    }

}
