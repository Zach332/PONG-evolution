package com.pong.main;

import java.awt.Color;

public class GameRun {

	private Handler handler;
	private Player player1;
	private Player player2;
	public GameState gamestate;
	public GameType gametype;
	private EvolvedTicker evolvedTicker;
	
	public enum GameState{
		PlayerTypeMenu,
		GameTypeMenu,
		Game,
		GameEnd;
	}
	
	public enum GameType{
		Normal,
		Evolved;
	}

	public GameRun(Handler handler) {
		handler.addGameRun(this);
		this.handler = handler;
		initializePlayerTypeMenu();
		//initializeGameTypeMenu(true);
		
	}
	
	public void initializeGameTypeMenu(boolean is2Player) {
		handler.removeAll();
		new Text("Controls:", Game.WIDTH/2 -190, 70, "Impact", 70, Color.WHITE, handler);
		if(!is2Player)
		{
			new Text("A: Up", Game.WIDTH/2 -190, 130, "Impact", 60, Color.WHITE, handler);
			new Text("Z: Down", Game.WIDTH/2 -190, 190, "Impact", 60, Color.WHITE, handler);
			new GameTypeButton(is2Player,400, 370, 90, "Normal", "Impact", 100, handler, this);
		} else {
			new Text("Player 1:", Game.WIDTH/2 -190, 120, "Impact", 40, Color.WHITE, handler);
			new Text("A: Up", Game.WIDTH/2 -170, 160, "Impact", 40, Color.WHITE, handler);
			new Text("Z: Down", Game.WIDTH/2 -170, 200, "Impact", 40, Color.WHITE, handler);
			new Text("Player 2:", Game.WIDTH/2 -190, 240, "Impact", 40, Color.WHITE, handler);
			new Text("P: Up", Game.WIDTH/2 -170, 280, "Impact", 40, Color.WHITE, handler);
			new Text("L: Down", Game.WIDTH/2 -170, 320, "Impact", 40, Color.WHITE, handler);
			new GameTypeButton(is2Player,450, 370, 100, "Normal", "Impact", 100, handler, this);
		}
		new GameTypeButton(is2Player,600, 420, 90, "Evolved", "Dialog ITALIC", 100, Color.BLUE, handler, this);
		
		gamestate = GameState.GameTypeMenu;
	}
	
	public void initializeGame(boolean is2Player) {
		handler.removeAll();
		new Player1(30,30,5,100,handler);
		if(is2Player) {new Player2(Game.WIDTH - 30, 30,5,100,handler);}
		else{new AIPlayer(Game.WIDTH - 30, 30,5,100,handler);}
		if(Game.r.nextInt(2) == 1) {
			new Ball(Game.WIDTH/4, Game.HEIGHT/2, 8, Game.r.nextInt(8)-4, 10, handler);
		}
		else {
			new Ball(Game.WIDTH/4 + Game.WIDTH/2, Game.HEIGHT/2, -8, Game.r.nextInt(8)-4, 10, handler);
		}
		boolean temp = true;
		for(int i = 0; i<handler.objects.size();i++)
		{
			if(handler.objects.get(i).getId() == ID.Player) {
				if(temp) {
					player1 = (Player)handler.objects.get(i); temp = false;
				}
				else {player2 = (Player)handler.objects.get(i);}
			}
		}
		handler.getHUD().initialize(player1,player2);
		gamestate = GameState.Game;
		if(gametype == GameType.Evolved)
		{
			evolvedTicker = new EvolvedTicker(handler, player1, player2, this);
		}
		
	}
	
	public void initializePlayerTypeMenu() {
		handler.removeAll();
		new Text("PONG", Game.WIDTH/2 -200, 140, "Impact", 150, Color.WHITE, handler);
		new Text("-evolution-", Game.WIDTH/2 -230, 250, "Dialog ITALIC", 80, Color.BLUE, handler);
		new PlayerTypeButton(400, 400, 90, "1 Player", "Impact", 100, handler, this);
		new PlayerTypeButton(600, 400, 90, "2 Player", "Impact", 100, handler, this);
		gamestate = GameState.PlayerTypeMenu;
	}
	
	

	public void tick() {

		if(gamestate == GameState.Game)
		{
			if(gametype ==  GameType.Evolved) {
				evolvedTicker.evolvedTick();
			} else {
				removeOutsideBalls();
			}
			
			if(player1.getScore() >=10 || player2.getScore() >=10) {
				for(int i = 0; i<handler.objects.size();i++)
				{
					if(handler.objects.get(i).getId() == ID.Ball) {
						handler.objects.remove(i);i--;
					}
				}
				new MenuButton(400, 290, 90, "Menu", "Impact", 100, handler, this);
				if(player1.getScore() >=10)
				{
					new Text("Player 1 wins", Game.WIDTH/2 -240, 250, "Impact", 80, Color.WHITE, handler);
				}
				if(player2.getScore() >=10)
				{
					new Text("Player 2 wins", Game.WIDTH/2 -240, 250, "Impact", 80, Color.WHITE, handler);
				}
			}
			
		}
		
		
	}

	public void removeOutsideBalls() {
		for(int i = 0; i<handler.objects.size();i++)
		{
			if(handler.objects.get(i).getId() == ID.Ball) {
				if(handler.objects.get(i).getX() < 0)
				{
					handler.objects.remove(i);i--;
					player2.setScore(player2.getScore() + 1);
					new Ball(Game.WIDTH/4, Game.HEIGHT/2, 8, Game.r.nextInt(8)-4, 10, handler);
				}
				if(handler.objects.get(i).getX() > Game.WIDTH)
				{
					handler.objects.remove(i);i--;
					player1.setScore(player1.getScore() + 1);
					new Ball(Game.WIDTH/4 + Game.WIDTH/2, Game.HEIGHT/2, -8, Game.r.nextInt(8)-4, 10, handler);
				}
			}
		}
	}
	
	
}

