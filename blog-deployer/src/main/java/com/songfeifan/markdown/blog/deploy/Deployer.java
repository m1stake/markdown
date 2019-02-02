package com.songfeifan.markdown.blog.deploy;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * 部署流程
 */
public class Deployer {

    private static final Runtime runtime = Runtime.getRuntime();

    /**
     * 日志项目根目录
     */
    private String blogBase;

    public Deployer(String blogBase) {
        this.blogBase = blogBase;
    }

    public void deploy() {
        exec("python3 deploy.py " + blogBase);
    }

    private void exec(String cmd) {
        exec(cmd, 180);
    }

    private void exec(String cmd, int timeout) {
        try {
            System.out.format(cmd + "%n");
            Process process = runtime.exec(cmd);
            process.waitFor(timeout, TimeUnit.SECONDS);
            int exitValue = process.exitValue();
            if (exitValue == 0) {
                print(process.getInputStream());
            } else {
                System.out.println("Error: " + exitValue);
                print(process.getErrorStream());
            }
        } catch (InterruptedException | IOException e) {
            System.out.print(" failed");
            throw new RuntimeException(e);
        }
    }

    private void print(InputStream in) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
