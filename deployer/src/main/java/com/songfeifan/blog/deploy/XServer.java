package com.songfeifan.blog.deploy;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class XServer {

    private static String key;

    private static String blogBase;

    public static void main(String[] args) throws Exception {

        setUpProps();

        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(10011);
        server.setConnectors(new Connector[] { connector });
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new CommitHandler(properties(), key)), "/commit");
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[] { context, new DefaultHandler() });
        server.setHandler(handlers);
        server.start();
        server.join();

    }

    private static void setUpProps() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                XServer.class.getClassLoader().getResourceAsStream("props")))) {
            key = reader.readLine().trim();
            blogBase = reader.readLine().trim();
        }
    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("blog.base", blogBase);
        return properties;
    }

}
