package prisonbreakgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {
	
	private Handler handler;
	private Guard guard;
	
	public Bullet(int x, int y, objectID id, Handler handler, int mx, int my, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		
		velX = (mx - x) / 10;
		velY = (my - y) / 10;
	}
	
    private void collision() {
    	
    	for(int i = 0; i < handler.object.size(); i++) {
    		GameObject tempObject = handler.object.get(i);
    		
    		if(tempObject.getId() == objectID.Block) {
    			
    			if (getBounds().intersects(tempObject.getBounds())) {
    				handler.removeObject(this);
    			}
    			
    			
    		}else if(tempObject.getId() == objectID.Door) {
    			if (getBounds().intersects(tempObject.getBounds())) {
    				handler.removeObject(this);
    			}
    			
    		}
    	}
    	
    }


	public void tick() {
		x += velX;
		y += velY;
		
		collision();
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, 10, 5);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 12, 8);
	}
	
	

}
