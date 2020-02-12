import java.awt.*;

public class Guard extends GameObject{

    Handler handler;


    public Guard(int x, int y, int hp, int damage, objectID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;  //Updates the x value per second
        y += velY;  //Updates the y value per second

        //Guard Movement
        
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 10, 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }
}
