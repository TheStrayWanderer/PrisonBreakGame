import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {


    Handler handler;
    Game game;
    private BufferedImage[] player_image = new BufferedImage[3];

    Animation anim;
    
    public Player(int x, int y, int hp, objectID id, Handler handler, Game game, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        
        player_image[0] = ss.grabImage(1, 1, 32, 32);
        player_image[1] = ss.grabImage(1, 2, 32, 32);
        player_image[2] = ss.grabImage(1, 3, 32, 32);
        
        anim = new Animation(3, player_image[0], player_image[1], player_image[2]);
        
    }
    

    public void tick() {

        x += velX;	//updates x position by adding the pixels it moves per second
        y += velY;  //updates y position by adding the pixels it moves per second
        
        collision();

        //Player Movement
        if(handler.isUp()) velY = -3;
        else if(!handler.isDown()) velY = 0;

        if(handler.isDown()) velY = 3;
        else if(!handler.isUp()) velY = 0;

        if(handler.isRight()) velX = 3;
        else if(!handler.isLeft()) velX = 0;

        if(handler.isLeft()) velX = -3;
        else if(!handler.isRight()) velX = 0;
        
        anim.runAnimation();

    }
    
    private void collision() {
    	
    	for(int i = 0; i < handler.object.size(); i++) {
    		GameObject tempObject = handler.object.get(i);
    		
    		if(tempObject.getId() == objectID.Block) {
    			
    			if (getBounds().intersects(tempObject.getBounds())) {
    				x += velX * -1;
    				y += velY * -1;
    			}
    			
    			
    		}else if(tempObject.getId() == objectID.Door) {
    			if (getBounds().intersects(tempObject.getBounds())) {
    				x += velX * -1;
    				y += velY * -1;
    			}
    		}else if(tempObject.getId() == objectID.Gun) {
    			if (getBounds().intersects(tempObject.getBounds())) {
    				game.ammo += 10;
    				game.hasGun = true;
    				handler.removeObject(tempObject);
    			}
    		}else if(tempObject.getId() == objectID.Guard) {
    			if(getBounds().intersects(tempObject.getBounds())) {
    				game.hp--;
    			}
    		}
    		}
    	}
    	
   
    public void render(Graphics g) {
    	if (velX == 0 && velY == 0) {
    		g.drawImage(player_image[0], x, y, null);
    	}else{
    		anim.drawAnimation(g, x, y, 0);
    	}
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
