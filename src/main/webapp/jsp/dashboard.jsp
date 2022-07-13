<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/styles.css"/>
<title>Dashboard</title>
</head>
<body>
    <header class=navbar>

      <a href="home.html">
           <div class="logo-image">
         <img alt="logo" src="imgs/BT-modified.png">
         </div>
      </a>

        <nav>
            <ul>
                <li ><a href="homepage">Home</a></li>
                <li ><a href="dashboard" class="currentTab">Dashboard</a></li>
                <li ><a href="settings">Settings</a></li>
                <li><a href="contact">Contact Us</a></li>
            </ul>
        </nav>

    </header>
	
		<main>
			<h1 class="dashheading">Dashboard</h1>
			<div class="budgetlist">
				<div class="box">
					<h1 id="month">January</h1>
					<h2 class="category">Monthly Income</h2>
					<p>${income_total}</p>
					<c:forEach var="item" items="${expense_list}">
						<h2>${item.getName()}</h2>
						<p>${item.getAmount() }</p>
					</c:forEach>
				</div>
				
				
			</div>
			<a href="/" ><div class="addform">
				<p>+</p>
			</div></a>
		</main>
 		
	
</body>
</html>