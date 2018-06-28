package com.pong.main;

import java.awt.Graphics;

public abstract class GameObject {

	protected int x;
	protected int y;
	protected int velX;
	protected int velY;
	protected int sizeX;
	protected int sizeY;
	protected ID id;
	public GameObject(int x, int y, int velX, int velY, int sizeX, int sizeY, ID id, Handler handler) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.id = id;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		handler.addObject(this);
	}
	public int getSizeX() {
		return sizeX;
	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void update(int key, boolean keyDown);
}
