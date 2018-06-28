package com.pong.main;

public abstract class Player extends GameObject{
	protected int score = 0;
	protected PlayerID playerID;
	protected boolean isImmobilized = false;
	

	public PlayerID getPlayerID() {
		return playerID;
	}

	public void setPlayerID(PlayerID playerID) {
		this.playerID = playerID;
	}

	public Player(int x, int y, int velX, int velY, int sizeX, int sizeY, ID id, PlayerID playerID, Handler handler) {
		super(x, y, velX, velY, sizeX, sizeY, id, handler);
		this.playerID = playerID;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public enum PlayerID{
		Human(),
		AI();
	}
	public void changeSizeY(int diff) {
		sizeY=Game.clamp(sizeY+diff, 5, 300);
	}
	public void setIsImmobilized(boolean val) {
		isImmobilized = val;
	}

}
