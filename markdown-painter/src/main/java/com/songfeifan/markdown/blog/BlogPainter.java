package com.songfeifan.markdown.blog;

import com.songfeifan.markdown.component.CodeBlock;
import com.songfeifan.markdown.component.StringBlock;
import com.songfeifan.markdown.freemarker.ConfigurationFactory;
import com.songfeifan.markdown.painter.CodePainter;
import com.songfeifan.markdown.painter.Painter;
import com.songfeifan.markdown.painter.PumlCodePainter;
import com.songfeifan.markdown.painter.StringPainter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class BlogPainter implements Painter<BlogDocument> {

    private StringPainter stringPainter = new StringPainter();
    private CodePainter codePainter = new CodePainter();

    {
        codePainter.putExtension("puml", new PumlCodePainter());
    }

    @Override
    public String paint(BlogDocument document) {
        Configuration cfg = ConfigurationFactory.get();

        Template tmp;
        try {
            tmp = cfg.getTemplate("article.ftlx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object>  model = new HashMap<>();
        model.put("doc", document);
        model.put("painter", this);
        StringWriter out = new StringWriter();
        try {
            tmp.process(model, out);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }

        return out.toString();
    }

    public String paintBlock(int index, StringBlock stringBlock) {
        return stringPainter.paint(index, stringBlock);
    }

    public String paintBlock(int index, CodeBlock codeBlock) {
        return codePainter.paint(index, codeBlock);
    }
}
