import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Menu extends JPanel{

    private BufferedImage image=null;
     public static boolean isStarted = false;
     public Menu() {
         LoadImage();
         this.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 super.mouseClicked(e);
                 isStarted=true;
             }
         });
     }
     public void LoadImage(){
       try{
           image=ImageIO.read(new File("images/welcomeground.png"));

       } catch(Exception e){
           e.printStackTrace();

       }


}
@Override
public void paint(Graphics g){
         super.paint(g);
         g.drawImage(image,0,0,700,850,null);

}
}
