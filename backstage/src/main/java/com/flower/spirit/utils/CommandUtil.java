package com.flower.spirit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import com.flower.spirit.config.Global;

public class CommandUtil {

    private static Logger logger = LoggerFactory.getLogger(CommandUtil.class);

    static Pattern pattern = Pattern.compile("\"(.*?)\"");

    public static void command(String command) {
        try {
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            } else {
                processBuilder = new ProcessBuilder("/bin/sh", "-c", command);
            }

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read command output
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Command executed with exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String steamcmd(String account, String password, String wallpaper) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("steamcmd", "+login " + account + " " + password + "",
                    "+workshop_download_item 431960 " + wallpaper + "", "+quit");
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            String path = "";
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    path = matcher.group(1);
                }
            }
            int exitCode = process.waitFor();
            System.out.println("SteamCMD执行完毕，退出码：" + exitCode);
            return path;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return wallpaper;
    }

    public static String commandos(String command) {
        StringBuilder output = new StringBuilder();
        try {
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            } else {
                processBuilder = new ProcessBuilder("/bin/sh", "-c", command);
            }

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // 读取 Python 脚本输出
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            // System.out.println("Python 脚本执行完毕，退出码：" + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return output.toString().trim();
    }

    public static String f2cmd(String cookie, String aid, String fuc, String uid, String cid, Integer maxc,
            String out) {
        StringBuilder cmd = new StringBuilder("/opt/venv/bin/python3 /home/app/script/douyin.py ");
        switch (fuc) {
            case "fetch_video":
                cmd.append("fetch_video ")
                        .append("--cookie \"").append(cookie).append("\" ")
                        .append("--aweme_id \"").append(aid).append("\"");
                break;

            case "fetch_user_like_videos":
            case "fetch_user_post_videos":
                cmd.append(fuc).append(" ")
                        .append("--cookie \"").append(cookie).append("\" ")
                        .append("--uid \"").append(uid).append("\" ")
                        .append("--maxc \"").append(maxc).append("\" ")
                        .append("--output \"").append(out).append("\"");
                break;

            case "fetch_user_collects":
                cmd.append("fetch_user_collects ")
                        .append("--cookie \"").append(cookie).append("\"");
                break;

            case "fetch_user_collects_videos":
                cmd.append("fetch_user_collects_videos ")
                        .append("--cookie \"").append(cookie).append("\" ")
                        .append("--cid \"").append(cid).append("\" ")
                        .append("--maxc \"").append(maxc).append("\" ")
                        .append("--output \"").append(out).append("\"");
                break;

            case "fetch_user_feed_videos":
                cmd.append("fetch_user_feed_videos ")
                        .append("--cookie \"").append(cookie).append("\" ")
                        .append("--uid \"").append(uid).append("\" ")
                        .append("--output \"").append(out).append("\"");
                break;

            default:
                throw new IllegalArgumentException("Unsupported function: " + fuc);
        }

        return CommandUtil.commandos(cmd.toString());
    }

    public static boolean deleteDirectory(String directoryPath) {
        // System.out.println(directoryPath);
        logger.error("[删除目录] 正在准备删除目录:" + directoryPath);
        if (directoryPath == null || directoryPath.trim().isEmpty()) {
            logger.error("[删除目录警告] 正在尝试删除空目录或根路径");
            return false;
        }

        try {
            // 规范化路径
            File directory = new File(directoryPath);
            String canonicalPath = directory.getCanonicalPath();
            String saveFileCanonical = new File(Global.uploadRealPath).getCanonicalPath();

            // 验证目标路径是否在允许的目录下
            if (!canonicalPath.startsWith(saveFileCanonical)) {
                logger.error("[删除目录警告] 正在删除白名单外的目录" + saveFileCanonical);
                return false;
            }

            // 验证目录是否存在
            if (!directory.exists() || !directory.isDirectory()) {
                logger.error("[删除目录警告] 目标目录不存在");
                return false;
            }

            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder = new ProcessBuilder("cmd.exe", "/c", "rmdir", "/s", "/q", canonicalPath);
            } else {
                processBuilder = new ProcessBuilder("rm", "-rf", canonicalPath);
            }

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info("[删除目录输出] " + line);
            }
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
