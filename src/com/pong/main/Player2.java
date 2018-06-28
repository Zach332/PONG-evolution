package com.pong.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player2 extends Player{
	

	public Player2(int x, int y, int sizeX, int sizeY, Handler handler) {
		super(x, y, 0, 0, sizeX, sizeY, ID.Player, Player.PlayerID.Human, handler);
	}

	@Override
	public void tick() {
		//x+=velX;
		if(!isImmobilized) {
			y= Game.clamp(y+velY, 0, Game.HEIGHT - (sizeY+28));
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, sizeX, sizeY);
		
	}

	@Override
	public void update(int key, boolean keyDown) {
		if(keyDown)
		{
			if(key == KeyEvent.VK_P) {
				velY = Game.clamp(velY-1,-16,-6);
				return;
			}
			if(key == KeyEvent.VK_L) {
				velY = Game.clamp(velY+1, 6, 16);
				return;
			}
		}
		else {
			if(key == KeyEvent.VK_P && velY<0) {
				velY = 0;
				return;
			}
			if(key == KeyEvent.VK_L && velY>0) {
				velY = 0;
			}
		}
		
	}

	

}
