package com.pong.main;

import java.awt.Color;
import java.awt.Graphics;

public class Powerup extends GameObject{
	
	private Color color;
	private String type;
	private Handler handler;
	private EvolvedTicker evolvedticker;
	
	public Powerup(int x, int y, ID id, String type, Color color, Handler handler, EvolvedTicker evolvedticker) {
		super(x, y, 0, 0, 10, 70, id, handler);
		this.type = type;
		this.handler = handler;
		this.evolvedticker = evolvedticker;
		this.color = color;
	}

	@Override
	public void tick() {
		for(int i = 0; i < handler.objects.size();i++)
		{
			if(handler.objects.get(i).id == ID.Ball) {
				if(Game.isTouching(this,handler.objects.get(i))) {
					evolvedticker.powerup(type,handler.objects.get(i).getVelX());
					handler.objects.remove(this);
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, sizeX, sizeY);
		g.setColor(color);
		
	}

	@Override
	public void update(int key, boolean keyDown) {
		// TODO Auto-generated method stub
		
	}

}
