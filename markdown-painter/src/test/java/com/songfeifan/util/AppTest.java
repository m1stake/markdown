package com.songfeifan.util;

import static org.junit.Assert.assertTrue;

import com.songfeifan.markdown.blog.BlogAnalysis;
import com.songfeifan.markdown.blog.BlogPainter;
import com.songfeifan.markdown.parser.*;
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
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        Class a = null;
        // assertTrue( a instanceof Class );
    }

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
                Objects.requireNonNull(AppTest.class.getClassLoader().getResource("a.md")).toURI()).toFile();
        byte[] bs = FileUtils.readFileToByteArray(mdFile);
        String md = new String(bs, StandardCharsets.UTF_8);
        BlockTree blockTree = new Parser().parse(md);
        String s = new BlogPainter().paint(new BlogAnalysis().analysis(blockTree));
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
