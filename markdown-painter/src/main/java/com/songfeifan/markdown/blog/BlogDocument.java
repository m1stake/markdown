package com.songfeifan.markdown.blog;

import com.songfeifan.markdown.painter.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlogDocument extends Document {

    private String title;

    private List<Paragraph> paragraphs = new ArrayList<>();
}
