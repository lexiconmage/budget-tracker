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

import jakarta.servlet.RequestDispatcher;


/**
 * Servlet implementation class ControllerServlet
 * 
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BudgetList list;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        
        list = new BudgetList();
        list.connect();
        list.disconnect();
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
			listBudget(request, response);
			break;
		case "/contact":
			System.out.println("HIT");
			getContact(request, response);
			break;
		default:
			response.sendError(0);
		}
	}

	//These are the methods that help with navigation. Waiting on DB and other functionalities to be placed
	private void getHome(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		request.getRequestDispatcher("/pages/home.html").forward(request, response);
	}
	
	private void getLogin(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		request.getRequestDispatcher("/pages/login.html").forward(request, response);
	}
	
	private void getSignup(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		request.getRequestDispatcher("/pages/signup.html").forward(request, response);
	}
	
/**
 * 	Not needed since we are using listBudget ---------------------------------------------------
 */
//	private void getDash(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
//
//		request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
//	}
	
	private void getContact(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		request.getRequestDispatcher("/pages/contact.html").forward(request, response);
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
	
	private void listBudget(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		ArrayList<BudgetList> budgetArrList = list.getList();
		
		request.setAttribute("budget_list", budgetArrList);
		request.getRequestDispatcher("/pages/dashboard.jsp").forward(request, response);
	}

}