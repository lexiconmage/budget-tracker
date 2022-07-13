<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/styles.css"/>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.8.0/chart.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/google-palette/1.1.0/palette.min.js"></script>

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
					<h1 class="month">January</h1>
					<div class="flexbox">
						<div class="leftbox">
							<h2 class="category">Monthly Income</h2>
							<p><fmt:formatNumber value="${income_total}" type="currency"/></p>
							<c:forEach var="item" items="${expense_list}">
								<h2>${item.getName()}</h2>
								<p><fmt:formatNumber value="${item.getAmount()}" type="currency"/></p>
							</c:forEach>
						</div>
						<div class="rightbox">
							<canvas id="budgetchart"></canvas>
						</div>
					</div>
				</div>
				
				
				
			</div>
			<a href="budgetform" ><div class="addform">
				<p>+</p>
			</div></a>
		</main>
 		
	
</body>
<script>
const budgetdata = [<c:forEach var="item" items="${expense_list}" varStatus="loop">
			${item.getAmount()}<c:if test="${!loop.last}">,</c:if>
		</c:forEach>]
const data = {
		  labels: [
		    <c:forEach var="item" items="${expense_list}" varStatus="loop">
		    	'${item.getName()}'<c:if test="${!loop.last}">,</c:if>
		    </c:forEach>
		  ],
		  datasets: [{
		    label: 'Budget Data',
		    data: budgetdata,
			backgroundColor: palette('cb-Spectral', budgetdata.length).map(function(hex) {
				return '#' + hex;
			}),
		    hoverOffset: 4
		  }]
		};
const config = {
		type: 'pie',
		data: data
};
const myChart = new Chart(document.getElementById("budgetchart"), config);
</script>
</html>