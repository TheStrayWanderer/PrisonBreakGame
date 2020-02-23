import java.awt.*;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;
    private static long lastFpsCheck = 0;
    private static int currentFps = 0;
    private static int totalFrames = 0;

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera cam;
    private SpriteSheet ss;
    
    private BufferedImage level_1;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;
    
    public int ammo = 100;
    public int hp = 200;
    public boolean hasGun = false;
    public boolean isInRange = false; 

    public Game(){
        new Window(1000, 1000, "PacDad", this);
        start();

        handler = new Handler();
        cam = new Camera(0, 0);
        this.addKeyListener(new PlayerKeyInput(handler));

        BufferedImageLoader loader = new BufferedImageLoader();
        level_1 = loader.loadImage("/level1.png");
        
        sprite_sheet = loader.loadImage("/sprite_sheet.png");
        
        ss = new SpriteSheet(sprite_sheet);
        
        floor = ss.grabImage(4, 1, 32, 32);
        
        this.addMouseListener(new MouseInput(handler, cam, this, ss));

        loadLevel1(level_1);
    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning){
        	totalFrames++;
        	if(System.nanoTime() > lastFpsCheck + 1000000000) {
        		lastFpsCheck = System.nanoTime();
        		currentFps = totalFrames;
        		totalFrames = 0;
        		
        	}
        	
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                //updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
                //updates = 0;
            }
        }
        stop();
    }

    public void tick(){
        
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == objectID.Player){
                cam.tick(handler.object.get(i));
            }
        }
        
        handler.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D G2D = (Graphics2D) g;
        /////////////////////////////////////

        
        
        G2D.translate(-cam.getX(), -cam.getY());
        
        for(int xx = 0; xx < 30 * 72; xx += 32) {
        	for(int yy = 0; yy < 30 * 72; yy += 32) {
        		g.drawImage(floor, xx, yy, null);
        	}
        }
        

        handler.render(g);
        
        G2D.translate(cam.getX(), cam.getY());
        
        ////////////////////////////////////
        
        
        /*HUD code below*/
        
        //Health bar code
        g.setColor(Color.gray);
        g.fillRect(5, 5, 200, 32);
        if (hp >= 100) {
        	 g.setColor(Color.green);
             g.fillRect(5, 5, hp, 32);
        }else if (hp >= 50) {
        	g.setColor(Color.orange);
        	g.fillRect(5, 5, hp, 32);
        }else if (hp >= 25){
        	g.setColor(Color.red);
        	g.fillRect(5, 5, hp, 32);
        }else if(hp == 0) {
        	g.setColor(Color.gray);
        	g.fillRect(5, 5, 200, 32);
        }

        g.setColor(Color.black);
        g.drawRect(5, 5, 200, 32);
        
        //Ammo display
        g.setColor(Color.WHITE);
        g.drawString("Ammo: " + ammo, 5, 70);
        
        g.setColor(Color.white);
        g.drawString("FPS: " + currentFps, 5, 90);
        
        g.dispose();
        bs.show();

    }
    
    //Loading Level 1
    private void loadLevel1(BufferedImage image) {
    	int w = image.getWidth();
    	int h = image.getHeight();
    	
    	for(int xx = 0; xx < w; xx++) {
    		for(int yy = 0; yy < h; yy++) {
    			int pixel = image.getRGB(xx, yy);
    			int red = (pixel >> 16) & 0xff;
    			int green = (pixel >> 8) & 0xff;
    			int blue = (pixel) & 0xff;
    			
    			if(red == 237) {
    				handler.addObject(new Block(xx * 32, yy * 32, objectID.Block, ss));
    			}
    			
    			if(blue == 160) {
    				handler.addObject(new Player(xx * 40, yy * 40, 100, objectID.Player, handler, this, ss));
    			}
    			
    			if(green == 127) {
    				handler.addObject(new Stairs(xx * 32, yy *32, objectID.Stairs, ss));
    			}
    			
    	/*		if (red == 127) {
    				handler.addObject(new Door(xx * 32, yy * 32, objectID.Door));
    			} */
    			
    			if (green == 177) {
    				handler.addObject(new Guard(xx * 30, yy * 30, 100, objectID.Guard, handler, ss));
    			}
    			
    			if (blue == 201) {
    				handler.addObject(new Gun(xx * 30, yy * 30, objectID.Gun, handler, ss));
    			}
    			
    		}
    	}
    }

    public static void main(String args[]){
        new Game();

    }

}
