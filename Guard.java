import java.awt.*;

public class Guard extends GameObject{

    Handler handler;

    public Guard(int x, int y, objectID id) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;  //Updates the x value per second
        y += velY;  //Updates the y value per second

        //Guard Movement
        if(handler.isUp()) velY = -3;
        else if(!handler.isDown()) velY = 0;

        if(handler.isDown()) velY = 3;
        else if(!handler.isUp()) velY = 0;

        if(handler.isRight()) velX = 3;
        else if(!handler.isLeft()) velX = 0;

        if(handler.isLeft()) velX = -3;
        else if(!handler.isRight()) velX = 0;

    }

    public void render(Graphics g) {

    }

    public Rectangle getBounds() {
        return null;
    }
}
