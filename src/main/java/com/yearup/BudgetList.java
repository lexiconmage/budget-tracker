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


public class BudgetList {
	private ArrayList<Expense> exp;
	private ArrayList<Income> inc;
	private int id;
	
	public BudgetList() {
		
	}
	
	public BudgetList(ArrayList<Expense> exp, ArrayList<Income> inc, int id) {
		this.id = id;
		this.exp.addAll(exp);
		this.inc.addAll(inc);
	}
	
	public void setIncList(ArrayList<Income> inc) {
		this.inc.clear();
		this.inc.addAll(inc);
	}
	
	public void setExpList(ArrayList<Expense> exp) {
		this.exp.clear();
		this.exp.addAll(exp);
	}
	
	public ArrayList<Expense> getExpList(){
		return this.exp;
	}
	
	public ArrayList<Income> getIncList(){
		return this.inc;
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
	
	public ArrayList<BudgetList> getList(){
		connect();
		ArrayList<BudgetList> budgetList = new ArrayList<>();
		try {
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM budgetlist");
			
			while(resultSet.next()) {
				int listId = resultSet.getInt("list_id");
				
				BudgetList item = new BudgetList(getExpenseList(),getIncomeList(), listId);
				budgetList.add(item);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return budgetList;
	}
	
	public float getCollatedIncome() {
		connect();
		float incomeTotal = 0;
		try {
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT SUM(amount) AS amount FROM budgetitem WHERE amount > 0");
			resultSet.next();
			incomeTotal = resultSet.getFloat("amount");
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect();
		return incomeTotal;
	}
	public ArrayList<Income> getIncomeList() {
		connect();
		ArrayList<Income> incomeList = new ArrayList<>();
		try {
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT title, amount FROM budgetitem WHERE amount > 0");
			
			while(resultSet.next()) {
				String incomeName = resultSet.getString("title");
				float incomeAmount = resultSet.getFloat("amount");
				
				Income income = new Income(incomeAmount,incomeName);
				incomeList.add(income);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
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
			ResultSet resultSet = statement.executeQuery("SELECT title, amount FROM budgetitem WHERE amount < 0");
			
			while(resultSet.next()) {
				String expenseName = resultSet.getString("title");
				float expenseAmount = resultSet.getFloat("amount");
				
				Expense expense = new Expense(expenseAmount, expenseName);
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
	public ArrayList<Expense> getCollatedExpenseList() {
		connect();
		ArrayList<Expense> expenseList = new ArrayList<>();
		try {
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT categories.category, SUM(budgetitem.amount) AS amount FROM budgetitem INNER JOIN categories on categories.title=budgetitem.title WHERE amount < 0 GROUP BY categories.category;");
			
			while(resultSet.next()) {
				String expenseName = resultSet.getString("category");
				float expenseAmount = resultSet.getFloat("amount");
				
				Expense expense = new Expense(expenseAmount, expenseName);
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
			statement.setFloat(2, -1 * exp.getAmount());
			
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
