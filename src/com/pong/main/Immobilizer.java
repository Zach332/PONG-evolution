package com.pong.main;

import java.awt.Color;
import java.awt.Graphics;

public class Immobilizer extends GameObject{
	private Player otherPlayer;
	private int ticks = 0;
	private Handler handler;

	public Immobilizer(int x, int y, int velX, Player otherPlayer, Handler handler) {
		super(x, y, velX, 0, 10, 10, ID.Immobilizer, handler);
		this.otherPlayer = otherPlayer;
		this.handler = handler;
	}

	@Override
	public void tick() {
		if(Game.isTouching(this, otherPlayer)) {
			otherPlayer.setIsImmobilized(true);
			ticks++;
			if(ticks>40) {
				otherPlayer.setIsImmobilized(false);
				handler.objects.remove(this);
			}
		} else {
			x+=velX;
			if(x>Game.WIDTH || x < 0) {
				handler.objects.remove(this);
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, sizeX, sizeY);
		
	}

	@Override
	public void update(int key, boolean keyDown) {
		
	}

}
