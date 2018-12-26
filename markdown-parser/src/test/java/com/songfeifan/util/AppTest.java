package com.songfeifan.util;

import static org.junit.Assert.assertTrue;

import com.songfeifan.util.markdown.parser.*;
import com.songfeifan.util.markdown.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException, URISyntaxException {
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
        System.out.println(blockTree);
    }

    @Test
    public void nextLine() {
        StringUtil.nextLine("# a", 0);
    }
}
