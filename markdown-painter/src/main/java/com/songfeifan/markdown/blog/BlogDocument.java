package com.songfeifan.markdown.blog;

import com.songfeifan.markdown.painter.Document;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BlogDocument implements Document {

    private String title;

    private List<Paragraph> paragraphs = new ArrayList<>();
}
