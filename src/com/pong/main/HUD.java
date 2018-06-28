package com.pong.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	private Handler handler;
	private Player player1;
	private Player player2;

	public HUD(Handler handler)
	{
		this.handler = handler;
	}
	
	
	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Impact", 50, 100));
		g.drawString(String.valueOf(player1.getScore()), Game.WIDTH/4, 100);
		g.drawString(String.valueOf(player2.getScore()), Game.WIDTH/4 + Game.WIDTH/2 - 30, 100);
	}
	
	public void initialize(Player player1, Player player2)
	{
		this.player1 = player1;
		this.player2 = player2;
	}
}
