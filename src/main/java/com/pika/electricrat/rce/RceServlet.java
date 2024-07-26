package com.pika.electricrat.rce;

import com.pika.electricrat.web.Annotation.Api;
import com.pika.electricrat.web.Annotation.RequestMethodType;
import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/rce/*")
public class RceServlet extends BaseServlet {
    @Api({RequestMethodType.POST})
    public Map<?, ?> cmd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
        String command = request.getParameter("cmd");
        HashMap<String, Object> data = new HashMap<>();
        data.put("data", execCmd(command));
        return data;
    }

    @Api({RequestMethodType.POST})
    public Map<?, ?> ping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
        String ip = request.getParameter("ip");
        HashMap<String, Object> data = new HashMap<>();
        int os = SystemInfo();
        String ping = "ping ";
        if (os == 0) {
            ping = "ping -n 4 ";
        } else if (os == 2) {
            ping = "ping -c 4 ";
        }
        data.put("data", execCmd(ping + ip));
        return data;
    }

    public static String execCmd(String cmd) throws IOException, InterruptedException {
        List<String> bash_cmd = new ArrayList<>();
        int os = SystemInfo();
        if (os == 0) {
            bash_cmd.add("cmd");
            bash_cmd.add("/c");
        } else if (os == 2) {
            bash_cmd.add("/bin/sh");
            bash_cmd.add("-c");
        }
        bash_cmd.add(cmd);
        Process p = Runtime.getRuntime().exec(bash_cmd.toArray(new String[bash_cmd.size()]));
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String line;
        StringBuilder text = new StringBuilder();
        while((line = reader.readLine())!= null){
            text.append(line).append("\n");
        }
        p.waitFor();
        is.close();
        reader.close();
        p.destroy();
        return text.toString();
    }

    public static int SystemInfo() {
        String operatingSystem = System.getProperty("os.name").toLowerCase();

        if (operatingSystem.contains("win")) {
            return 0;
        } else if (operatingSystem.contains("mac")) {
            return 1;
        } else if (operatingSystem.contains("nix") || operatingSystem.contains("gnu") || operatingSystem.contains("aix") || operatingSystem.contains("linux") || operatingSystem.contains("bsd") || operatingSystem.contains("solaris")) {
            return 2;
        } else {
            return 3;
        }
    }
}
