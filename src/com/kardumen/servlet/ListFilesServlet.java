package com.kardumen.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class ListFilesServlet
 */
public class ListFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListFilesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	Properties prop = new Properties();
    	InputStream input = new FileInputStream("config.properties");
    	// load a properties file
    	prop.load(input);

    	File[] files = new File(prop.getProperty("path")).listFiles();
		List<String> names= new ArrayList<String>();
		for(File f:files){
			if(f.isFile())
				names.add(f.getName());
		}
		
		
		Gson gson=new GsonBuilder().create();
	    String jsonArray = gson.toJson(names);
	    request.setAttribute("files", jsonArray);
	    
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
