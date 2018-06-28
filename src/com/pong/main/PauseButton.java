package com.pong.main;

import java.awt.Color;

public class PauseButton extends Button{
	private Handler handler;
	private MenuButton menubutton;

	public PauseButton(int x, int y, int sizeX, int sizeY, String message, String font, int fontSize, Color color,
			Handler handler, GameRun gamerun) {
		super(x, y, sizeX, sizeY, message, font, fontSize, color, handler, gamerun);
		this.handler = handler;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY) {
		if(mouseX>=boxX && mouseX<=boxX+boxSizeX)
		{
			if(mouseY>=boxY && mouseY<=boxY+boxSizeY) {
				if(gamerun.gamestate == GameRun.GameState.Paused) {
					gamerun.gamestate = GameRun.GameState.Game;
					handler.objects.remove(menubutton);
				} else {
					gamerun.gamestate = GameRun.GameState.Paused;
					menubutton = new MenuButton(400, 290, 90, "Menu", "Impact", 100, handler, gamerun);
				}
			}
		}
		
	}

}
