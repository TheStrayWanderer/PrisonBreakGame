import java.awt.*;
import java.util.LinkedList;

public class Handler {

    /*Creates a list that links all Game Objects allowing
    easier control of creation and removal of objects.
     */
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    //Player movement boolean values
    private boolean up = false, down = false, right = false, left = false;
    
    //Other general key bindings
    private boolean pickUp = false;

    public boolean isPickUp() {
		return pickUp;
	}

	public void setPickUp(boolean pickUp) {
		this.pickUp = pickUp;
	}

	public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject tempObject){

        object.add(tempObject);
    }

    public void removeObject(GameObject tempObject){

        object.remove(tempObject);
    }

}
