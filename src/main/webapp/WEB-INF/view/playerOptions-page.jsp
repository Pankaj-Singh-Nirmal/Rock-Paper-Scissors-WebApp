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
				<c:when test="${onePlayerGameFormat == 'yes'}">
					<div class="scoreboard">
						<div class="display-inline float-left">PLAYER 1</div>
						<div class="display-inline float-center">vs</div>
						<div class="display-inline float-right">COMPUTER</div>
					</div>
					<div class="score float-left display-inline margin-left-300">${player1Score}</div>
					<div class="player-comments">PLAYER 1: Take Your Pick !</div>
					<div class="score float-right display-inline margin-right-300">${player2Score}</div>
					<br><br>
					<form action="processOnePlayerGame" method="get">
						<div class="margin-left-100">
							<label>
								<input type="radio" name="playerOneSelection" value="ROCK" checked>
								<img src="/images/rock.png">
							</label>
							<label>
								<input type="radio" name="playerOneSelection" value="PAPER">
								<img src="/images/paper.png">
							</label>
							<label>
								<input type="radio" name="playerOneSelection" value="SCISSORS">
								<img src="/images/scissors.png">
							</label>
						</div>
						<br><br>
						<input class="custom-button" type="submit" value="Submit"/>
					</form>
				</c:when>
				
				<c:when test="${twoPlayerGameFormat == 'yes'}">
					<div class="scoreboard">
						<div class="display-inline float-left">PLAYER 1</div>
						<div class="display-inline float-center">vs</div>
						<div class="display-inline float-right">PLAYER 2</div>
					</div>
					<div class="score float-left display-inline margin-left-300">${player1Score}</div>
					<div class="player-comments">PLAYER 1: Take Your Pick !</div>
					<div class="player-remarks">* Make sure PLAYER 2 is not watching ! *</div>
					<div class="score float-right display-inline margin-right-300">${player2Score}</div>
					<br><br>
					<form action="processTwoPlayerGame-player-1" method="post">
						<div class="margin-left-100">
							<label>
								<input type="radio" name="playerOneSelection" value="ROCK" checked>
								<img src="/images/rock.png">
							</label>
							<label>
								<input type="radio" name="playerOneSelection" value="PAPER">
								<img src="/images/paper.png">
							</label>
							<label>
								<input type="radio" name="playerOneSelection" value="SCISSORS">
								<img src="/images/scissors.png">
							</label>
						</div>
						<br><br>
						<input class="custom-button" type="submit" value="Submit"/>
					</form>
				</c:when>
				
				<c:otherwise>
					<div class="scoreboard">
						<div class="display-inline float-left">PLAYER 1</div>
						<div class="display-inline float-center">vs</div>
						<div class="display-inline float-right">PLAYER 2</div>
					</div>
					<div class="score float-left display-inline margin-left-300">${player1Score}</div>
					<div class="player-comments">PLAYER 2: Take Your Pick !</div>
					<div class="score float-right display-inline margin-right-300">${player2Score}</div>
					<br><br>
					<form action="processTwoPlayerGame-player-2" method="post">
						<div class="margin-left-100">
							<label>
								<input type="radio" name="playerTwoSelection" value="ROCK" checked>
								<img src="/images/rock.png">
							</label>
							<label>
								<input type="radio" name="playerTwoSelection" value="PAPER">
								<img src="/images/paper.png">
							</label>
							<label>
								<input type="radio" name="playerTwoSelection" value="SCISSORS">
								<img src="/images/scissors.png">
							</label>
						</div>
						<br><br>
						<input class="custom-button" type="submit" value="Submit"/>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
		
		<a href="/" class="custom-button home">
			<i class="fas fa-home fa-fw"></i>
		</a>
	</body>
</html>