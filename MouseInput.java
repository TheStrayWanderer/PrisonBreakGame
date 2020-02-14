import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
    
    Handler handler;
    
    public MouseInput(Handler handler){
        this.handler = handler;
    }
    
    
    public void MousePressed(MouseEvent e){
        
        int button = e.getButton();
        
        for (int i = 0; i < handler.object.size(); i++){
            
            GameObject tempObject = handler.object.get(i);
            
            if (tempObject.getId() == objectID.Player){
                
            }
            
        }
        
    }
    
    
}
