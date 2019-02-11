package com.songfeifan.blog.analyzer;

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
