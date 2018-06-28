package com.pong.main;

import java.awt.Color;

public class GameTypeButton extends Button{
	private boolean is2Player;

	public GameTypeButton(boolean is2Player, int y, int sizeX, int sizeY, String message, String font, int fontSize,
			Handler handler, GameRun gamerun) {
		super(Game.WIDTH/2 - sizeX/2, y, sizeX, sizeY, message, font, fontSize, handler, gamerun);
		this.is2Player = is2Player;
	}
	
	public GameTypeButton(boolean is2Player, int y, int sizeX, int sizeY, String message, String font, int fontSize, Color color,
			Handler handler, GameRun gamerun) {
		super(Game.WIDTH/2 - sizeX/2, y, sizeX, sizeY, message, font, fontSize, color, handler, gamerun);
		this.is2Player = is2Player;
	}

	public void mouseClicked(int mouseX, int mouseY) {
		if(mouseX>=boxX && mouseX<=boxX+boxSizeX)
		{
			if(mouseY>=boxY && mouseY<=boxY+boxSizeY) {
				if(message == "Normal") {
					gamerun.gametype = GameRun.GameType.Normal;	
				}else {
					gamerun.gametype = GameRun.GameType.Evolved;
				}
				gamerun.initializeGame(is2Player);
			}
		}
	}

}