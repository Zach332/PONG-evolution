package com.pong.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Text extends GameObject{
	
	private String font;
	private int size;
	private Color color;
	private String message;

	public Text(String message, int x, int y, String font, int size, Color color, Handler handler) {
		super(x, y, 0, 0, 0, 0, ID.Text, handler);
		this.font = font;
		this.size = size;
		this.color = color;
		this.message = message;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.setFont(new Font(font,1,size));
		g.drawString(message, x, y);
		
	}

	@Override
	public void update(int key, boolean keyDown) {
		// TODO Auto-generated method stub
		
	}

}
