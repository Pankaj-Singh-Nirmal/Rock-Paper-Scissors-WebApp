<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<link rel="stylesheet" href="/css/styles.css"/>
		<link rel="stylesheet" href="/css/materialize.min.css"/>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
	</head>
	<body>
		<div class="center">
			<c:choose>
				<c:when test="${gameFormat == 'onePlayerGame'}">
					<div class="scoreboard">
						<div class="display-inline float-left">PLAYER 1</div>
						<div class="display-inline float-center">vs</div>
						<div class="display-inline float-right">COMPUTER</div>
					</div>
					<div class="score float-left display-inline margin-left-300">${player1Score}</div>
					<div class="player-comments">RESULT: 
						<c:if test="${result == 'Player 2 Won !'}">
						    Computer Won !
						</c:if>
						<c:if test="${result != 'Player 2 Won !'}">
						    ${result}
						</c:if>
					</div>
					<div class="score float-right display-inline margin-right-300">${player2Score}</div>
					<br><br>
					
					<div class="margin-left-100">
						<div class="display-inline">${playerOneSelectionImgUrl}</div>
						<div class="display-inline">${computerSelectionImgUrl}</div>
					</div>
					
					<c:choose>
						<c:when test="${alternateGameFormat == 'yes'}">
							<form:form action="/twoPlayerGame" method="get">
								<input id="continue-button-margin" type="submit" class="custom-button" value="Continue"/>
							</form:form>
						</c:when>
						
						<c:otherwise>
							<div class="left-div">
								<form:form action="/resetGameForOnePlayerGame" method="get">
									<input id="reset-button-margin" type="submit" class="custom-button" value="Reset"/>
								</form:form>
							</div>
							
							<div class="right-div">
								<form:form action="/onePlayerGame" method="get">
									<input id="continue-button-margin" type="submit" class="custom-button" value="Continue"/>
								</form:form>
							</div>
						</c:otherwise>
					</c:choose>
				</c:when>
				
				<c:when test="${gameFormat == 'twoPlayerGame'}">
					<div class="scoreboard">
						<div class="display-inline float-left">PLAYER 1</div>
						<div class="display-inline float-center">vs</div>
						<div class="display-inline float-right">PLAYER 2</div>
					</div>
					<div class="score float-left display-inline margin-left-300">${player1Score}</div>
					<div class="player-comments">RESULT: ${result}</div>
					<div class="score float-right display-inline margin-right-300">${player2Score}</div>
					<br><br>
					
					<div class="margin-left-100">
						<div class="display-inline">${playerOneSelectionImgUrl}</div>
						<div class="display-inline">${playerTwoSelectionImgUrl}</div>
					</div>
					
					<c:choose>
						<c:when test="${alternateGameFormat == 'yes'}">
							<form:form action="/onePlayerGame" method="get">
								<input id="continue-button-margin" type="submit" class="custom-button" value="Continue"/>
							</form:form>
						</c:when>
						
						<c:otherwise>
							<div class="left-div">
								<form:form action="/resetGameForTwoPlayerGame" method="get">
									<input id="reset-button-margin" type="submit" class="custom-button" value="Reset"/>
								</form:form>
							</div>
							
							<div class="right-div">
								<form:form action="/twoPlayerGame" method="get">
									<input id="continue-button-margin" type="submit" class="custom-button" value="Continue"/>
								</form:form>
							</div>
						</c:otherwise>
					</c:choose>
				</c:when>
				
				<c:otherwise>
					<div class="error_headline">
						Oops ! Someone just reloaded the page. Please click the home button to start fresh !
					</div>
					<div class="icon_finger">&#9756;</div>
				</c:otherwise>
			</c:choose>
		</div>
		
		<a href="/" class="custom-button home">
			<i class="fas fa-home fa-fw"></i>
		</a>
		
	</body>
</html>