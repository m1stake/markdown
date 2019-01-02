package com.songfeifan.markdown.blog;

import com.songfeifan.markdown.component.Component;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Paragraph {

    private String header;

    private List<Component> blocks = new ArrayList<>();

}
