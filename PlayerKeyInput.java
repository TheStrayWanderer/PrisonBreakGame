package prisonbreakgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerKeyInput extends KeyAdapter {

    Handler handler;

    public PlayerKeyInput(Handler handler){
        this.handler = handler;
    }

    /*
    Code below is known as a Key Listener, as the name
    suggests it 'listens' to keys and obtains the player
    input allowing for player movement
     */
    public void keyPressed(KeyEvent k){
        int key = k.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == objectID.Player){
                if(key == KeyEvent.VK_W) handler.setUp(true);
                if(key == KeyEvent.VK_S) handler.setDown(true);
                if(key == KeyEvent.VK_A) handler.setLeft(true);
                if(key == KeyEvent.VK_D) handler.setRight(true);
                if(key == KeyEvent.VK_E) handler.setPickUp(true);
            }
        }


    }

    /*
    The code below is for when the key is released
     */
    public void keyReleased(KeyEvent k) {

        int key = k.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == objectID.Player){
                if(key == KeyEvent.VK_W) handler.setUp(false);
                if(key == KeyEvent.VK_S) handler.setDown(false);
                if(key == KeyEvent.VK_A) handler.setLeft(false);
                if(key == KeyEvent.VK_D) handler.setRight(false);
                if(key == KeyEvent.VK_E) handler.setPickUp(false);
            }
        }

    }
}
