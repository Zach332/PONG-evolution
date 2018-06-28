package com.pong.main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private GameRun gamerun;
	private HUD hud;
	
	public void addGameRun(GameRun gamerun) {
		this.gamerun = gamerun;
		hud = new HUD(this);
	}

	public void tick() {
		if(!(gamerun.gamestate == GameRun.GameState.Paused)) {
			for(int i = 0; i<objects.size();i++)
			{
				objects.get(i).tick();
			}
			gamerun.tick();
		} else {
			for(int i = 0; i<objects.size();i++)
			{
				if(objects.get(i).getId() != ID.Ball && objects.get(i).getId() != ID.Player && objects.get(i).getId() != ID.Immobilizer) {
					objects.get(i).tick();
				}
			}
			gamerun.tick();
		}
	}

	public void render(Graphics g) {

		for(int i = 0; i<objects.size();i++)
		{
			objects.get(i).render(g);
		}
		if(gamerun.gamestate == GameRun.GameState.Game || gamerun.gamestate == GameRun.GameState.Paused || gamerun.gamestate == GameRun.GameState.GameEnd)
		{
			hud.render(g);
		}
	}
	
	public void addObject(GameObject o) {
		objects.add(o);
	}
	
	public HUD getHUD() {
		return hud;
	}
	
	public void removeAll() {
		for(int i = 0; i < objects.size(); i++)
		{
			objects.remove(i);i--;
		}
	}
}
