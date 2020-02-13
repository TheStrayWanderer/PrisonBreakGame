import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera cam;

    private BufferedImage level_1;


    public Game(){
        new Window(1000, 1000, "PacDad", this);
        start();

        handler = new Handler();
        cam = new Camera(0, 0);
        this.addKeyListener(new PlayerKeyInput(handler));

        BufferedImageLoader loader = new BufferedImageLoader();
        level_1 = loader.loadImage("/level1.png");

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
        
        g.setColor(Color.black);
        g.fillRect(0, 0, 1000, 1000);
        
        G2D.translate(-cam.getX(), -cam.getY());

        handler.render(g);
        
        G2D.translate(cam.getX(), cam.getY());
        
        ////////////////////////////////////
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
    				handler.addObject(new Block(xx * 32, yy * 32, objectID.Block));
    			}
    			
    			if(blue == 160) {
    				handler.addObject(new Player(xx * 10, yy * 10, 100, 100, objectID.Player, handler));
    			}
    			
    			if(green == 127) {
    				handler.addObject(new Stairs(xx * 32, yy *32, objectID.Stairs));
    			}
    			
    	/*		if (red == 127) {
    				handler.addObject(new Door(xx * 32, yy * 32, objectID.Door));
    			} */
    		}
    	}
    }

    public static void main(String args[]){
        new Game();
    }

}
