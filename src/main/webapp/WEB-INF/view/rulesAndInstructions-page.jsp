<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<link rel="stylesheet" href="/css/styles.css"/>
		<link rel="stylesheet" href="/css/materialize.min.css"/>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col m12">
					<div id="rgba-bg" class="card">
						<div class="card-content">
							<h3 class="color-cyan center">RULES & INSTRUCTIONS</h3>
							<br>
							<span class="info-message">
								According to the rules, ROCK overrides SCISSORS, PAPER overrides ROCK and SCISSORS override PAPER. In case of same choices, a DRAW is called and nobody is awarded any point. In case of a win, winner is awarded one point.
							</span>
							<span class="info-message">
								There are 3 Game modes viz. 1 PLAYER, 2 PLAYERS and ALTERNATE. If you wanna play with computer then choose 1 PLAYER. If you wanna play with a friend, then choose 2 PLAYERS. If you wanna keep switching the game modes after every game then choose ALTERNATE.
							</span>
							<span class="info-message">
								When playing a 2 PLAYERS game, it must be made sure that when it's PLAYER-1's turn, then PLAYER-2 is not watching the choice selected by PLAYER-1, otherwise it will be an unfair game.	
							</span>
							<span class="info-message">
								After selecting the game mode, just click on any one image from ROCK, PAPER or SCISSORS. A red box around the image is an indication that your choice is locked. After locking the choice click the SUBMIT button. After clicking the SUBMIT button, in a 1 PLAYER game, result will be shown at this step. In a 2 PLAYERS game at this step PLAYER 2 gets their turn. After repeating the same steps as PLAYER 1, PLAYER 2 submits their choice. After clicking the submit button players can see the result with their entered choices.
							</span>
							<span class="info-message">
								After choosing the game mode, if at any point of time you decide you wanna play a different mode, then press the HOME icon on the top left corner. It will take you to the home page where you can choose the game mode again.
							</span>
							<span class="info-message">
								There is a possibility of either continuing with the current game scores or to reset the scores and play again. To continue with the current scores press CONTINUE. To reset the current scores and play again, press RESET. In case the game mode is ALTERNATE, RESET buttons is not provided since the CONTINUE button works the same way as RESET button in the ALTERNATE mode.
							</span>
							<span class="info-message center">
								Have fun !!!
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<a href="/" class="custom-button home">
			<i class="fas fa-home fa-fw"></i>
		</a>
	</body>
</html>