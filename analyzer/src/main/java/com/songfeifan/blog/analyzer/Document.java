package com.songfeifan.blog.analyzer;

import lombok.Data;

import java.util.Map;

@Data
public abstract class Document {

    private Map<String, Object> documentMeta;

}
