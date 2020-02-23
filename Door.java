import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Door extends GameObject {

	public Door(int x, int y, objectID id, SpriteSheet ss) {
		super(x, y, id,ss);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	

	
}
