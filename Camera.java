package prisonbreakgame;

public class Camera {
	
	private float x, y;
	
	public Camera(float x, float y) {
		this.y = y;
		this.x = x;
		
	}
	
	public void tick(GameObject object) {
		
		x += ((object.getX() - x) - 1000/2) * 0.05f;
		y += ((object.getY() - y) - 1000/2) * 0.05f;
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	

}
