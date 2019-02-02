package com.songfeifan.markdown.painter;

import lombok.Data;

import java.util.Map;

@Data
public abstract class Document {

    private Map<String, Object> documentMeta;

}
