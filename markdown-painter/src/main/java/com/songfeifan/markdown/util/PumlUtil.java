package com.songfeifan.markdown.util;

import net.sourceforge.plantuml.Option;
import net.sourceforge.plantuml.Run;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class PumlUtil {

    public static String convertToSvg(String code) {
        Option option;
        try {
            option = new Option("-tsvg", "-p");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        StringReader in = new StringReader(code);
        BufferedReader br = new BufferedReader(in);
        StringWriter out = new StringWriter();
        PrintStream ps = new PrintStream(new WriterOutputStream(out, StandardCharsets.UTF_8));
        try {
            Run.managePipe(option, br, ps);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toString();
    }

}
