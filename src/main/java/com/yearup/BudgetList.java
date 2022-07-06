package com.yearup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	public ArrayList<Income> getIncomeList() {
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
	public ArrayList<Expense> getExpenseList() {
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
		float totalExpense = 0;
		for(Expense exp : getExpenseList()){
			totalExpense += exp.getAmount();
		}
		
		float totalIncome = 0;
		for(Income inc : getIncomeList()) {
			totalIncome += inc.getAmount();
		}
		return totalIncome - totalExpense;
	}
	
	public boolean addExpense(Expense exp) {
		connect();
		String sql = "INSERT INTO budgetitem (title, amount) VALUES (?,?)";
		boolean rowInserted = false;
		try {
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, exp.getName());
			statement.setFloat(2, exp.getAmount());
			
			rowInserted = statement.executeUpdate() > 0;
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
		return rowInserted;
	}
	public boolean addIncome(Income inc) {
		connect();
		String sql = "INSERT INTO budgetitem (title, amount) VALUES (?,?)";
		boolean rowInserted = false;
		try {
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, inc.getName());
			statement.setFloat(2, inc.getAmount());
			
			rowInserted = statement.executeUpdate() > 0;
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
		return rowInserted;
	}
}
