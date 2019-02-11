package com.songfeifan.blog.paint.freemarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.PlainTextOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public abstract class ConfigurationFactory {

    private static Configuration cfg;

    public static Configuration get() {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_28);
            TemplateLoader ctl = new ClassTemplateLoader(ConfigurationFactory.class, "/template");
            cfg.setTemplateLoader(ctl);
            cfg.setOutputFormat(PlainTextOutputFormat.INSTANCE);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
        }
        return cfg;
    }

}
