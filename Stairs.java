package prisonbreakgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Stairs extends GameObject{

	public Stairs(int x, int y, objectID id) {
		super(x, y, id);

	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
