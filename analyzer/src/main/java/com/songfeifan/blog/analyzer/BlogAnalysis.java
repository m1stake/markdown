package com.songfeifan.blog.analyzer;

import com.songfeifan.blog.analyzer.exception.DocumentFormatException;
import com.songfeifan.blog.parse.BlockTree;
import com.songfeifan.blog.parse.component.CodeBlock;
import com.songfeifan.blog.parse.component.Component;
import com.songfeifan.blog.parse.component.Header;
import com.songfeifan.blog.parse.component.StringBlock;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlogAnalysis implements Analyzer<BlogDocument> {

    @Override
    public BlogDocument analysis(BlockTree blockTree) {
        BlogDocument document = new BlogDocument();

        documentMeta(document, blockTree);
        title(document, blockTree);
        paragraphs(document, blockTree.getNext());

        return document;
    }

    private void documentMeta(BlogDocument document, BlockTree blockTree) {
        List<Map<String, Object>> metaList = new ArrayList<>();
        Component component;
        while ((component = blockTree.getComponent()) != null) {
            if (component instanceof CodeBlock) {
                CodeBlock codeBlock = (CodeBlock) component;
                if ("meta".equals(codeBlock.getLanguage())) {
                    metaList.add(parseMeta(codeBlock.getCode()));
                }
            }
            blockTree = blockTree.getNext();
        }
        document.setDocumentMeta(mergeMeta(metaList));
    }

    private Map<String, Object> mergeMeta(List<Map<String, Object>> metaList) {
        Map<String, Object> meta = new HashMap<>();
        for (Map<String, Object> m: metaList) {
            meta.putAll(m);
        }
        return meta;
    }

    private Map<String, Object> parseMeta(String code) {
        Map<String, Object> meta = new HashMap<>();
        Pattern pattern = Pattern.compile("\\s*([^:]+)\\s*:\\s*(.+)\\s*");
        String[] lines = code.split("\r|\n|\r\n");
        for (String line: lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                meta.put(matcher.group(1), matcher.group(2));
            }
        }
        return meta;
    }

    private void title(BlogDocument document, BlockTree blockTree) {
        Component component = blockTree.getComponent();
        if ((component instanceof Header) && ((Header) component).getLevel() == 1) {
            Header header = (Header) component;
            String title = header.getContent().toString();
            document.setTitle(title);
        } else {
            throw new DocumentFormatException("文档格式错误，应以Title（Header, level=1）开始文档的第一行");
        }
    }

    private void paragraphs(BlogDocument document, BlockTree paragraphBlocks) {
        Component component = paragraphBlocks.getComponent();
        if (component == null) {
            return;
        }

        // 空行
        while (component instanceof StringBlock) {
            if (StringUtils.isNotEmpty(((StringBlock) component).getContent())) {
                throw new DocumentFormatException("文档格式错误，应以Header（Header, level=2）开始段落的第一行");
            }
            paragraphBlocks = paragraphBlocks.getNext();
            component = paragraphBlocks.getComponent();
        }

        while (!endOfDocument(paragraphBlocks.getComponent())) {
            paragraphBlocks = paragraph(document, paragraphBlocks);
        }
    }

    private BlockTree paragraph(BlogDocument document, BlockTree paragraphBlocks) {

        Component component = paragraphBlocks.getComponent();
        if (paragraphStart(component)) {
            Paragraph paragraph = new Paragraph();
            Header header = (Header) component;
            paragraph.setHeader(header.getContent().toString());

            paragraphBlocks = paragraphBlocks.getNext();
            Component nextComponent = paragraphBlocks.getComponent();
            while (!paragraphStart(nextComponent) && !endOfDocument(nextComponent)) {
                paragraph.getBlocks().add(nextComponent);
                paragraphBlocks = paragraphBlocks.getNext();
                nextComponent = paragraphBlocks.getComponent();
            }

            document.getParagraphs().add(paragraph);
        } else {
            throw new DocumentFormatException("文档格式错误，应以Header（Header, level=2）开始段落的第一行");
        }

        return paragraphBlocks;
    }

    private boolean endOfDocument(Component component) {
        return component == null;
    }

    private boolean paragraphStart(Component component) {
        return (component instanceof Header) && (((Header) component).getLevel() == 2);
    }
}
