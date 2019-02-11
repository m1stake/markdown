package com.songfeifan.blog.deploy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class CommitHandler extends HttpServlet {

    private final String key;

    private final Properties properties;

    public CommitHandler(Properties properties, String key) {
        this.key = key;
        this.properties = properties;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            if (key.equals(req.getParameter("key"))) {
                new Deployer(properties.getProperty("blog.base")).deploy();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500, "Deploy failed");
        }
    }
}
