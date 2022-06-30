package com.yearup;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class ControllerServlet
 * 
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//This action notices the change in path and uses switch case to determine which method to use
		
		String action = request.getPathInfo();
		
		switch(action) {
		case "/homepage":
			getHome(request, response);
			break;
		case "/login":
			getLogin(request, response);
			break;
		case "/signup":
			getSignup(request, response);
			break;
		case "/dashboard":
			getDash(request, response);
			break;
		}
	}

	//These are the methods that help with navigation. Waiting on DB and other functionalities to be placed
	private void getHome(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		request.getRequestDispatcher("/homepage.jsp").forward(request, response);
	}
	
	private void getLogin(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	private void getSignup(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		request.getRequestDispatcher("/signup.jsp").forward(request, response);
	}
	
	private void getDash(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 *  WAITING FOR CREATE BUDGET TO BE IMPLEMENTED. THEN WE CAN WORK ON POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}