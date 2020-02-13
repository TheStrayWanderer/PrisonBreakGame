public class Camera {
	
	private float x, y;
	
	public Camera(float x, float y) {
		this.y = y;
		this.x = x;
		
	}
	
	public void tick(GameObject object) {
		
		x += ((object.getX() - x) - 1000/2) * 0.05f;
		y += ((object.getY() - y) - 1000/2) * 0.05f;
		
		if(x <= 0) x = 0;
		if(x >= 1060) x = 1060;
		if(y <= 0) y = 0;
		if(y >= 190) y = 190;
		
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

