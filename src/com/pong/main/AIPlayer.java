package com.pong.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class AIPlayer extends Player{
	
	//private Ball ball;
	private int counter = 0;
	private int speed = 2;
	private Handler handler;


	public AIPlayer(int x, int y, int sizeX, int sizeY, Handler handler) {
		super(x, y, 0, 0, sizeX, sizeY, ID.Player, Player.PlayerID.AI, handler);
		this.handler = handler;
	}

	@Override
	public void tick() {
		//x+=velX;
		counter++;
		ArrayList<Ball> balls = new ArrayList<Ball>();
		for(int i = 0; i < handler.objects.size(); i++) {
			if(handler.objects.get(i).getId() == ID.Ball) {
				Ball temp = (Ball) handler.objects.get(i);
				balls.add(temp);
			}
		}
		Ball ball = null;
		int highest = Integer.MIN_VALUE;
		int highestIndex = -1;
		for(int i = 0; i < balls.size(); i++) {
			int place = balls.get(i).getX() * balls.get(i).getVelX();
			if(place > highest) {
				highest = place;
				highestIndex = i;
			}
		}
		if(highestIndex!=-1) {
			ball = balls.get(highestIndex);
		}
		
		
		if(!isImmobilized) {
			if(ball!=null) {
				if(counter%speed == 0)
				{
					if(ball.getY() < y+1 + sizeY/2)
					{
						velY = Game.clamp(velY-1,-16,-1);
					}
					if(ball.getY() > y-1 + sizeY/2) {
						velY = Game.clamp(velY+1, 1, 16);
					}
				}
			}
			
			y= Game.clamp(y+velY, 0, Game.HEIGHT - (sizeY+28));
		}
		
	}
	/*
	public void addBall(Ball ball) {
		this.ball = ball;
	}*/

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, sizeX, sizeY);
		
	}

	@Override
	public void update(int key, boolean keyDown) {
		
	}
	
	

}
