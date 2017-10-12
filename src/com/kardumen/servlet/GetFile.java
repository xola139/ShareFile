package com.kardumen.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFile extends HttpServlet {
	static final long serialVersionUID = 1L;
    private static final int BUFSIZE = 4096;
    String filePath;
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    	Properties prop = new Properties();
    	InputStream input = new FileInputStream("config.properties");
    	// load a properties file
    	prop.load(input);

    	filePath= prop.getProperty("path") + File.separator + request.getParameter("name");
        
        File file = new File(filePath);
        int length = 0;
        ServletOutputStream outStream = response.getOutputStream();
        response.setContentType("text/html");
        response.setContentLength((int) file.length());
        String fileName = (new File(filePath)).getName();
        response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
 
        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file));
 
        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            outStream.write(byteBuffer, 0, length);
        }
 
        in.close();
        outStream.close();
    }
}