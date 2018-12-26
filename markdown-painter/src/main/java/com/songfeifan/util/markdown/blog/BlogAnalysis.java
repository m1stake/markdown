package com.songfeifan.util.markdown.blog;

import com.songfeifan.util.markdown.analysis.Analyzer;
import com.songfeifan.util.markdown.blog.exception.DocumentFormatException;
import com.songfeifan.util.markdown.component.Component;
import com.songfeifan.util.markdown.component.Header;
import com.songfeifan.util.markdown.component.StringBlock;
import com.songfeifan.util.markdown.parser.BlockTree;
import org.apache.commons.lang.StringUtils;

public class BlogAnalysis implements Analyzer<BlogDocument> {

    @Override
    public BlogDocument analysis(BlockTree blockTree) {
        BlogDocument document = new BlogDocument();

        title(document, blockTree);
        paragraphs(document, blockTree.getNext());

        return document;
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
