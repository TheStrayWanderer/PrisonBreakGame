import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private Camera cam;
	private Game game;
	private SpriteSheet ss;
	private Player player;
	
	public MouseInput(Handler handler, Camera cam, Game game, SpriteSheet ss) {
		
		this.handler = handler;
		this.cam = cam;
		this.game = game;
		this.ss = ss;
		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = (int) (e.getX() + cam.getX());
		int my = (int) (e.getY() + cam.getY());
		
		for (int i = 0; i < handler.object.size(); i++) {
				
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == objectID.Player && game.ammo >= 1) {
				if (game.hasGun == true) {
					handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, objectID.Bullet, handler, mx, my, ss));
					game.ammo--;
				}
				
				
			}
				
		}
		
	}
	
}
