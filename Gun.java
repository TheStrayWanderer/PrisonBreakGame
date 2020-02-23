package prisonbreakgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Gun extends GameObject {

	private Handler handler;
	
	public Gun(int x, int y, objectID id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.handler = handler;

	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	
	
}
