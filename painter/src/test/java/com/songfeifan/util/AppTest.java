package com.songfeifan.util;

import com.songfeifan.blog.analyzer.BlogAnalysis;
import com.songfeifan.blog.analyzer.BlogDocument;
import com.songfeifan.blog.paint.BlogPainter;
import com.songfeifan.blog.parse.BlockTree;
import com.songfeifan.blog.parse.Context;
import com.songfeifan.blog.parse.Parser;
import com.songfeifan.blog.parse.visitor.CodeVisitor;
import com.songfeifan.blog.parse.visitor.HTMLVisitor;
import com.songfeifan.blog.parse.visitor.HeaderVisitor;
import com.songfeifan.blog.parse.visitor.StringVisitor;
import net.sourceforge.plantuml.Option;
import net.sourceforge.plantuml.Run;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.WriterOutputStream;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test() throws IOException, URISyntaxException {
        Context.blockVisitors.addAll(Arrays.asList(
                new CodeVisitor(),
                new HeaderVisitor(),
                new StringVisitor() ));

        Context.inLineBlockVisitors.addAll(Arrays.asList(
                new HTMLVisitor(),
                new StringVisitor() ));
        File mdFile = Paths.get(
                Objects.requireNonNull(AppTest.class.getClassLoader().getResource("encryption_communication.md")).toURI()).toFile();
        byte[] bs = FileUtils.readFileToByteArray(mdFile);
        String md = new String(bs, StandardCharsets.UTF_8);
        BlockTree blockTree = new Parser().parse(md);
        BlogDocument document = new BlogAnalysis().analysis(blockTree);
        String s = new BlogPainter().paint(document);
        System.out.println(s);
    }

    @Test
    public void testUml() throws IOException, InterruptedException {
        Option option = new Option("-tsvg", "-p");
        StringReader in = new StringReader("");
        StringWriter out = new StringWriter();
        Run.managePipe(option, new BufferedReader(in), new PrintStream(new WriterOutputStream(out, StandardCharsets.UTF_8)));
        System.out.println(out.toString());
    }
}
