package com.pong.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public abstract class Button extends GameObject{
	
	protected GameRun gamerun;
	protected int fontSize;
	protected String font;
	protected String message;
	protected int boxX;
	protected int boxY;
	protected int boxSizeX;
	protected int boxSizeY;
	protected Color color = Color.white;

	public Button(int x, int y, int sizeX, int sizeY, String message, String font, int fontSize, Handler handler, GameRun gamerun) {
		super(x, y, 0, 0, sizeX, sizeY, ID.Button, handler);
		this.gamerun = gamerun;
		this.fontSize = fontSize;
		this.message = message;
		this.font = font;
		this.boxX = x - (int)(sizeX * .05);
		this.boxY = y-sizeY;
		this.boxSizeX = sizeX;
		this.boxSizeY = (int)(sizeY*1.2);
	}
	
	public Button(int x, int y, int sizeX, int sizeY, String message, String font, int fontSize, Color color, Handler handler, GameRun gamerun) {
		super(x, y, 0, 0, sizeX, sizeY, ID.Button, handler);
		this.gamerun = gamerun;
		this.fontSize = fontSize;
		this.message = message;
		this.font = font;
		this.boxX = x - (int)(sizeX * .05);
		this.boxY = y-sizeY;
		this.boxSizeX = sizeX;
		this.boxSizeY = (int)(sizeY*1.2);
		this.color = color;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(boxX, boxY, boxSizeX , boxSizeY);
		g.setColor(color);
		g.setFont(new Font(font,1,fontSize));
		g.drawString(message, x, y);
	}

	@Override
	public void update(int key, boolean keyDown) {
		
	}
	
	public abstract void mouseClicked(int mouseX, int mouseY);

	
}
