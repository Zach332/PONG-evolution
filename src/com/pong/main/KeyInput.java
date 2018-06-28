package com.pong.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler h) {
		handler = h;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i<handler.objects.size();i++)
		{
			handler.objects.get(i).update(key,true);
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i<handler.objects.size();i++)
		{
			handler.objects.get(i).update(key,false);
		}
	}
}
