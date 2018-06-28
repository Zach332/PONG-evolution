package com.pong.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends GameObject{

	Handler handler;
	int ticksX = 0;
	private Player player1;
	private Player player2;
	public Ball(int x, int y, int velX, int velY, int size, Handler handler) {
		super(x, y, velX, velY, size, size, ID.Ball, handler);
		this.handler = handler;
		/*
		for(int i = 0; i<handler.objects.size();i++)
		{
			if(handler.objects.get(i).getId() == ID.Player) {
				Player tempPlayer = (Player)handler.objects.get(i);
				if(tempPlayer.getPlayerID() == Player.PlayerID.AI)
				{
					AIPlayer temp = (AIPlayer)handler.objects.get(i);
					temp.addBall(this);
				}
			}
		}
		*/
		
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
	}

	@Override
	public void tick() {
		ticksX++;
		// TODO Auto-generated method stub
		if((y<0)&&velY<0) {
			velY*=-1;
		}
		if(y> Game.HEIGHT -(sizeX+28) && velY>0) {
			velY*=-1;
		}
		
		if(Game.isTouching(this,player1)) {
			if(ticksX>10) {
				velX*=-1;ticksX = 0;
			}
			int center = player1.getY() + player1.getSizeY()/2;
			velY+=((y+sizeY/2) - center)/6;
			if(Math.abs(velY) < 20)
			velY+=(player1.getVelY())/2;
			velY = Game.clamp(velY, -14, 14);
		}
		if(Game.isTouching(this,player2)) {
			if(ticksX>10) {
				velX*=-1;ticksX = 0;
			}
			int center = player2.getY() + player2.getSizeY()/2;
			velY+=((y+sizeY/2) - center)/6;
			if(Math.abs(velY) < 20)
			velY+=(player2.getVelY())/2;
			velY = Game.clamp(velY, -14, 14);
		}
		x+=velX;
		y+=velY;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval(x, y, sizeX, sizeY);
	}

	@Override
	public void update(int key, boolean keyDown) {		
	}
	
	public void changeSize(int diff) {
		sizeX = Game.clamp(sizeX+diff, 3, 100);
		sizeY = Game.clamp(sizeY+diff, 3, 100);
	}

}
