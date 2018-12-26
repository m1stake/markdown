package com.songfeifan.util.markdown.blog;

import com.songfeifan.util.markdown.painter.Document;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BlogDocument implements Document {

    private String title;

    private List<Paragraph> paragraphs = new ArrayList<>();
}
