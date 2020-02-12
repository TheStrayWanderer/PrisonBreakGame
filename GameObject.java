import java.awt.*;
import java.awt.*;

public abstract class GameObject {

    protected int x, y;
    protected float velX = 0, velY = 0;
    protected objectID id;
    protected int hp;
    protected int damage;

    public GameObject(int x, int y, objectID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getDamage(){
        return damage;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getHP(){
        return hp;
    }

    public void setHP(int hp){
        this.hp = hp;
    }

    public objectID getId(){
        return id;
    }

    public void setId(objectID id){
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
