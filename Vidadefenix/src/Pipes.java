import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
public class Pipes extends Obstacle {


    private Random rand = new Random();
    public int xC, yC=rand.nextInt(MainPanel.HEIGHT/2+30);
    private int width = 78;
    private BufferedImage pipe = null;
    public int speed= -8;


    public Pipes(int xC){
        super(xC);
        this.xC=xC;
        LoadImage();

    }

    public void LoadImage(){
        try {
            pipe = ImageIO.read(new File("images/pieeeee.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void drawObstacle(Graphics g) {
       g.drawImage(pipe, xC,yC, null); //bottom

    }
    public void moveObstacle(){
       xC+=speed;
       if(xC<=-width){
           xC=MainPanel.WIDTH;
           yC=rand.nextInt(MainPanel.HEIGHT/2+30);
       }
       // Rectangle bottom = new Rectangle(xC,yC,width,height);
        Ellipse2D.Double ellipse = new Ellipse2D.Double(xC, yC, 85, 400);
        if(ellipse.intersects(Phoenix.birdArea())){
            boolean choose = MainPanel.gameOverMsg();
            if(choose){
                try{
                    Thread.sleep(500);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                Phoenix.reset();
                resetObstacle();
                MainPanel mp1= new MainPanel();
                mp1.secondspassed=0;

            }
            else{
                System.exit(0);
                GFrame.timer.stop();



            }
        }

    }

    public void resetObstacle(){
        yC=rand.nextInt(MainPanel.HEIGHT/2+30);
        MainPanel.GameOver= true;
    }







}
