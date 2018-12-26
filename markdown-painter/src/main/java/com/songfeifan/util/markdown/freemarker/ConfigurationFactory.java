package com.songfeifan.util.markdown.freemarker;

import freemarker.core.PlainTextOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public abstract class ConfigurationFactory {

    private static Configuration cfg;

    public static Configuration get() {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_28);

            URL url = ConfigurationFactory.class.getClassLoader().getResource("template");
            try {
                cfg.setDirectoryForTemplateLoading(new File(Objects.requireNonNull(url).toURI()));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
            cfg.setOutputFormat(PlainTextOutputFormat.INSTANCE);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
        }
        return cfg;
    }

}
