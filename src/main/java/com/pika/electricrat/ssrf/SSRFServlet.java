package com.pika.electricrat.ssrf;

import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.Base64Utils;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@WebServlet("/ssrf/*")
public class SSRFServlet extends BaseServlet {
    public void getRemoteImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("imageURL");
        try {
            URL u = new URL(url);
            URLConnection urlConnection = u.openConnection();
            BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
            List<Byte> buffer = new ArrayList<>();
            int length = -1;
            while ((length = in.read()) != -1){
                buffer.add((byte) length);
            }
            in.close();
            byte[] image2 = new byte[buffer.size()];
            for (int i = 0; i < buffer.size(); i++){
                image2[i] = buffer.get(i);
            }
            response.getWriter().append("data:image/jpeg;base64,").append(
                    new String(Base64.getEncoder().encode(image2)));
        } catch (Exception e){
            response.setStatus(400);
        }
    }
}
