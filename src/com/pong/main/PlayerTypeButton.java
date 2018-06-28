package com.pong.main;

public class PlayerTypeButton extends Button{

	public PlayerTypeButton(int y, int sizeX, int sizeY, String message, String font, int fontSize,
			Handler handler, GameRun gamerun) {
		super(Game.WIDTH/2 - sizeX/2, y, sizeX, sizeY, message, font, fontSize, handler, gamerun);
	}

	public void mouseClicked(int mouseX, int mouseY) {	
		if(mouseX>=boxX && mouseX<=boxX+boxSizeX)
		{
			if(mouseY>=boxY && mouseY<=boxY+boxSizeY) {
				if(message == "1 Player") {
					gamerun.initializeGameTypeMenu(false);
				}
				else {
					gamerun.initializeGameTypeMenu(true);
				}
			}
		}
	}

}
