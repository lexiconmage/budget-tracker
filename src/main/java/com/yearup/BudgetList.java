package com.yearup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pluralsight.Book;

public class BudgetList {
	public BudgetList() {
		
	}
	private Connection jdbcConnection;
	
	public void connect() {
		try {
			jdbcConnection = new DataConnector().getConnection();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void disconnect() {
		try {
			if(jdbcConnection != null && !jdbcConnection.isClosed()) {
				jdbcConnection.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Income> IncomeList() {
		//TODO: implement
		connect();
		ArrayList<Income> incomeList = new ArrayList<>();
		try {
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("");
			
			while(resultSet.next()) {
				String incomeName = resultSet.getString("incomeName");
				Date date = resultSet.getDate("date");
				float incomeAmount = resultSet.getFloat("incomeAmount");
				
				Income income = new Income(incomeAmount,incomeName,date);
				incomeList.add(income);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
		return incomeList;
	}
	public List<Expense> ExpenseList() {
		connect();
		//TODO: implement
		//getFloat(mortgage")
		//getFloat("rent")
		//getFloat(Utilities);
		//getFloat("Cable")
		//getFloat("water/gas");
		ArrayList<Expense> expenseList = new ArrayList<>();
		try {
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("");
			
			while(resultSet.next()) {
				String expenseName = resultSet.getString("name");
				Date date = resultSet.getDate("date");
				float expenseAmount = resultSet.getFloat("amount");
				
				Expense expense = new Expense(expenseAmount, expenseName, date);
				expenseList.add(expense);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
		return expenseList;
	}
	public float netIncome() {
		//TODO: implement
		//income - expense

		return 0;
	}
	
	public void addExpense(float value, String title, Date date) {
		//TODO: implement
	}
	public void addIncome(float value, String title, Date date) {
		//TODO: implement
	}
}
