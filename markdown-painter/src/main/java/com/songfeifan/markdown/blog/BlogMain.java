package com.songfeifan.markdown.blog;

import com.songfeifan.markdown.freemarker.ConfigurationFactory;
import com.songfeifan.markdown.parser.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BlogMain {

    public static void main(String args[]) throws IOException {
        initContext();

        File[] mds = mdFiles(args);
        int fileIndex = 0;
        List<ArticleMeta> articles = new ArrayList<>();
        for (File f: mds) {
            byte[] bs = FileUtils.readFileToByteArray(f);
            String md = new String(bs, StandardCharsets.UTF_8);
            BlogDocument document;
            String articleDoc;
            try {
                BlockTree blockTree = new Parser().parse(md);
                document = new BlogAnalysis().analysis(blockTree);
                articleDoc = new BlogPainter().paint(document);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            // 写文件
            String fileName = String.format("A%04d", fileIndex++);
            FileUtils.write(new File("article", fileName + ".js"), articleDoc, StandardCharsets.UTF_8);
            // 生成db信息
            ArticleMeta articleMeta = articleMeta(fileName, document);
            articles.add(articleMeta);
        }
        // 生成db文件
        String db = paintDb(articles);
        FileUtils.write(new File("db.js"), db, StandardCharsets.UTF_8);
    }

    private static void initContext() {
        Context.blockVisitors.addAll(Arrays.asList(
                new CodeVisitor(),
                new HeaderVisitor(),
                new StringVisitor() ));

        Context.inLineBlockVisitors.addAll(Arrays.asList(
                new HTMLVisitor(),
                new StringVisitor() ));
    }

    private static ArticleMeta articleMeta(String fileName, BlogDocument document) {
        ArticleMeta articleMeta = new ArticleMeta();
        articleMeta.setFileName(fileName);
        return articleMeta;
    }

    private static String paintDb(List<ArticleMeta> articles) {
        Configuration cfg = ConfigurationFactory.get();
        Template tmp;
        try {
            tmp = cfg.getTemplate("db.ftlx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> model = new HashMap<>();
        model.put("articles", articles);
        StringWriter out = new StringWriter();
        try {
            tmp.process(model, out);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException(e);
        }
        return out.toString();
    }

    private static File[] mdFiles(String[] args) {

        if (args == null || args.length == 0) {
            throw new RuntimeException("Usage: cmd mdFilePath/mdBasePath");
        }

        String p = args[0];
        File f = new File(p);
        if (f.isFile()) {
            return new File[] { f };
        } else {
            return f.listFiles(File::isFile);
        }
    }
}
