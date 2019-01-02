package com.songfeifan.markdown.painter;

import com.songfeifan.markdown.component.CodeBlock;
import com.songfeifan.markdown.util.PumlUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class PumlCodePainter implements CodeExtensionPainter {

    private static final String SVG_DIR = "svg";

    @Override
    public String paint(int index, CodeBlock component) {
        String svgCode = PumlUtil.convertToSvg(component.getCode());
        String fileName = UUID.randomUUID().toString().replaceAll("-", "").concat(".svg");
        try {
            FileUtils.write(new File(SVG_DIR, fileName), svgCode, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format("<ReactSVG key='%d' src='/svg/%s' />", index, fileName);
    }
}
