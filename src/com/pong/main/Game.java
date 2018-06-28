package com.pong.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.*;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1550691097823471818L;
	public static final int WIDTH = 960, HEIGHT = WIDTH / 12 * 9;
	Handler handler;
	public static Random r;
	private Thread thread;
	private Window window;
	private GameRun gamerun;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		handler = new Handler();
		gamerun = new GameRun(handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, gamerun));
		window = new Window(WIDTH, HEIGHT, "PONG evolution", this);
		r = new Random();
	}

	private void tick() {
		handler.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;

			}
			render();
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public static int clamp(int val, int min, int max) {
		if(val<=min)return min;
		if(val>=max)return max;
		return val;
	}
	
	public static boolean isTouching(GameObject o1, GameObject o2) {
		if(o1.getX() <= o2.getX() + o2.getSizeX() && o2.getX() <= o1.getX() + o1.getSizeX())
		{
			if(o1.getY() <= o2.getY() + o2.getSizeY() && o2.getY() <= o1.getY() + o1.getSizeY()) return true;
		}
		return false;
	}
	
	public static ArrayList pickRandomString(HashMap<String,Color> arr) {
		ArrayList ret = new ArrayList();
		ArrayList temp = new ArrayList(arr.keySet());
		String value = (String) temp.get(r.nextInt(temp.size()));
		ret.add(value);
		ret.add(arr.get(value));
		
		return ret;
	}

}

/*
 * TODO list
 * back button
 * 
 * 
 */
