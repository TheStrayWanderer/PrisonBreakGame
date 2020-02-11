import java.awt.*;

public class Player extends GameObject{


    Handler handler;

    public Player(int x, int y, objectID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {

        x += velX;
        y += velY;

        //Player Movement
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
        g.setColor(Color.yellow);
        g.fillRect(x, y, 10, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
}
