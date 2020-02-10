import java.awt.*;

public class Player extends GameObject{


    public Player(int x, int y) {
        super(x, y);

        velX = 1;
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, 10, 10);
    }

    public Rectangle getBounds() {
        return null;
    }
}
