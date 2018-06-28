package com.pong.main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class EvolvedTicker {
	
	private Handler handler;
	private Player player1;
	private Player player2;
	private int player1Immobilizer = 0;
	private int player2Immobilizer = 0;
	private int velX = 8;
	private int ballSize = 10;
	private GameRun gamerun;
	private HashMap<String,Color> map = new HashMap<String,Color>();
	private int numBalls = 1;
	
	
	private int ticks = 0;
	public EvolvedTicker(Handler handler, Player player1, Player player2, GameRun gamerun) {
		this.handler = handler;
		this.player1 = player1;
		this.player2 = player2;
		this.gamerun = gamerun;
		map.put("Bigger Ball", Color.WHITE);
		map.put("Smaller Ball", Color.WHITE);
		map.put("Bigger Paddle", Color.GREEN);
		map.put("Smaller Paddle", Color.RED);
		map.put("Immobilizer", Color.GREEN);
		map.put("Add Ball", Color.WHITE);
	}
	
	public void evolvedTick() {
		ticks++;
		removeOutsideBalls();
		if(ticks%250==0) {
			ArrayList temp = Game.pickRandomString(map);
			new Powerup(Game.r.nextInt(Game.WIDTH-60-10) +30, Game.r.nextInt(Game.HEIGHT-90), ID.Powerup, (String)temp.get(0), (Color)temp.get(1), handler, this);
		}
		if(player1Immobilizer >0) {
			player1Immobilizer++;
			if(player1Immobilizer%50==0) {
				new Immobilizer(player1.getX(), player1.getY() + player1.getSizeY()/2, 5, player2, handler);
			}
			if(player1Immobilizer > 400) {
				player1Immobilizer = 0;
			}
		}
		if(player2Immobilizer >0) {
			player2Immobilizer++;
			if(player2Immobilizer%50==0) {
				new Immobilizer(player2.getX(), player2.getY() + player2.getSizeY()/2, -5, player1, handler);
			}
			if(player2Immobilizer > 400) {
				player2Immobilizer = 0;
			}
		}
		
		
	}
	
	public void removeOutsideBalls() {
		for(int i = 0; i<handler.objects.size();i++)
		{
			if(handler.objects.get(i).getId() == ID.Ball) {
				if(handler.objects.get(i).getX() < 0)
				{
					handler.objects.remove(i);i--;numBalls--;
					player2.setScore(player2.getScore() + 1);
					if(numBalls==0) {
						numBalls++;
						new Ball(Game.WIDTH/4, Game.HEIGHT/2, velX, Game.r.nextInt(8)-4, ballSize, handler);
					}
				}
				if(handler.objects.get(i).getX() > Game.WIDTH)
				{
					handler.objects.remove(i);i--;numBalls--;
					player1.setScore(player1.getScore() + 1);
					if(numBalls==0) {
						numBalls++;
						new Ball(Game.WIDTH/4 + Game.WIDTH/2, Game.HEIGHT/2, velX * -1, Game.r.nextInt(8)-4, ballSize, handler);
					}
				}
			}
		}
	}
	
	public void powerup(String message, int velX)
	{
		if(message == "Bigger Ball") {
			ballSize+=5;
			for(int i = 0; i < handler.objects.size();i++)
			{
				if(handler.objects.get(i).id == ID.Ball) {
					Ball temp = (Ball)(handler.objects.get(i));
					temp.changeSize(5);
				}
			}
		}
		if(message=="Smaller Ball") {
			ballSize = Game.clamp(ballSize-5, 3, 100);
			for(int i = 0; i < handler.objects.size();i++)
			{
				if(handler.objects.get(i).id == ID.Ball) {
					Ball temp = (Ball)(handler.objects.get(i));
					temp.changeSize(-5);
				}
			}
		}
		if(message == "Smaller Paddle") {
			if(velX>0) {
				player1.changeSizeY(-10);
			} else {
				player2.changeSizeY(-10);
			}
		}
		if(message == "Bigger Paddle") {
			if(velX>0) {
				player1.changeSizeY(10);
			} else {
				player2.changeSizeY(10);
			}
		}
		if(message == "Immobilizer") {
			if(velX>0) {
				player1Immobilizer = 1;
			} else {
				player2Immobilizer = 1;
			}
		}
		if(message == "Add Ball") {
			numBalls++;
			if(velX>0) {
				new Ball(Game.WIDTH/4 + Game.WIDTH/2, Game.HEIGHT/2, velX * -1, Game.r.nextInt(8)-4, ballSize, handler);
			} else {
				new Ball(Game.WIDTH/4, Game.HEIGHT/2, velX * -1, Game.r.nextInt(8)-4, ballSize, handler);
			}
		}
	}
	
	
	
	/*
	 * Power-ups
	 * 
	 * increase/decrease paddle size DONE
	 * shoot immobilizing lasers DONE
	 * change ball size DONE
	 * extra ball DONE
	 * 
	 */

}
