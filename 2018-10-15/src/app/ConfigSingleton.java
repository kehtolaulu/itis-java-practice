package app;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;

class ConfigSingleton {
    private static Configuration cfg;

    static Configuration getConfig(ServletContext sc) {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_19);
            cfg.setServletContextForTemplateLoading(
                    sc,
                    "/WEB-INF/templates"
            );

            cfg.setTemplateExceptionHandler(
                    TemplateExceptionHandler.HTML_DEBUG_HANDLER
            );
        }
        return cfg;
    }
}
