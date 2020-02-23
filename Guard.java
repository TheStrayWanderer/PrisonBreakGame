import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Guard extends GameObject {

    private Handler handler;
    private int hp;
    private BufferedImage[] guard_image = new BufferedImage[3];
    private Bullet bullet;
    private boolean goingDown = true;
    private boolean playerInRange = false;

    Animation anim;
    Player player;
    Camera cam;
    

    public Guard(int x, int y, int hp, objectID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.hp = hp;
        
        guard_image[0] = ss.grabImage(2, 1, 32, 32);
        guard_image[1] = ss.grabImage(2, 2, 32, 32);
        guard_image[2] = ss.grabImage(2, 3, 32, 32);
        
        anim = new Animation(3, guard_image[0], guard_image[1], guard_image[2]);
        
    }
    
    private void collision() {
    	
    	for(int i = 0; i < handler.object.size(); i++) {
    		GameObject tempObject = handler.object.get(i);
    		
    		if(goingDown == true) {
    			velY = 1;
    			if(tempObject.getId() == objectID.Block) {
        			if(!getBoundsBig().intersects(tempObject.getBounds())) {
        				goingDown = false;
        			}
        			
        			
        		}
    		}else {
    			velY = -1;
    			if(tempObject.getId() == objectID.Block) {
        			if(!getBoundsBig().intersects(tempObject.getBounds())) {
        				goingDown = true;
        			}
        			
        			
        		}
    		}
    		
    	
    		
    		if (tempObject.getId() == objectID.Bullet) {
    			
    			if (getBounds().intersects(tempObject.getBounds())) {
    				hp -= 50;
    				if (hp >= 1) {
    					handler.removeObject(this);
    					handler.removeObject(bullet);
    				}
    				
    			}
    			
    		}
    		
    	}
    	
    }


    public void tick() {
        x += velX;  //Updates the x value per second
        y += velY;  //Updates the y value per second
        
        collision();
        
        anim.runAnimation();

        
    }
   
    
    public void render(Graphics g) {
    	if (velX == 0 && velY == 0) {
    		g.drawImage(guard_image[0], x, y, null);
    	}else {
    		anim.drawAnimation(g, x, y, 0);
    	}
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 40);
    }
    
    public Rectangle getBoundsBig() {
        return new Rectangle(x, y, 64, 64);
    }
    
    public Rectangle getAttackBounds() {
    	return new Rectangle(x+192, y+64, 192, 64);
    }
}
