package com.pong.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

	private Handler handler;
	private GameRun gamerun;

	public MouseInput(Handler h, GameRun gamerun) {
		handler = h;
		this.gamerun = gamerun;
	}

	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		GameRun.GameState state = gamerun.gamestate;
		for(int i = 0; i<handler.objects.size();i++)
		{
			if(handler.objects.get(i).id == ID.Button)
			{
				Button temp = (Button)handler.objects.get(i);
				if(gamerun.gamestate == state)
					temp.mouseClicked(mouseX,mouseY);
			}
		}

	}
}
