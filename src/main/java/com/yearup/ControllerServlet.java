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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
		case "/budgetform":
			getBudgetform(request, response);
			break;
		case "/contact":
			getContact(request, response);
			break;
		case "/settings":
			getSettings(request, response);
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
	
	private void getBudgetform(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

		request.getRequestDispatcher("/pages/budgetform.html").forward(request, response);
	}
	
	private void getContact(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		request.getRequestDispatcher("/pages/contact.html").forward(request, response);
	}
	
	private void getSettings(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		request.getRequestDispatcher("/pages/settings.html").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 *  WAITING FOR CREATE BUDGET TO BE IMPLEMENTED. THEN WE CAN WORK ON POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		switch(action) {
		case "/signup":
			doSignup(request, response);
			break;
		case "/login":
			doLogin(request, response);
			break;
		case "/budgetform":
			doBudget(request, response);
			break;
		}
	}
	
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		new User(email, password).login();
	}
	
	private void doSignup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		new User(name, email, password).create();
	}
	
	private void doBudget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> parameters = request.getParameterMap();
		for(String key: parameters.keySet()) {
			if(key.equals("salary") || key.equals("incomeother")) {
				list.addIncome(new Income(Integer.parseInt(parameters.get(key)[0]), key) );
			}
			else {
				list.addExpense(new Expense(Integer.parseInt(parameters.get(key)[0]), key) );
			}
		}
		request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
	}
	
	private void listBudget(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		float incomeTotal = list.getCollatedIncome();
		ArrayList<Expense> expenseList = list.getCollatedExpenseList();
		request.setAttribute("income_total", incomeTotal);
		request.setAttribute("expense_list", expenseList);
		request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
	}

}