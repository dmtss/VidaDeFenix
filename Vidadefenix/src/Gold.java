import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Gold extends Obstacle {

    public static int golds;
    private Random rand = new Random();
    public int xC, yC=rand.nextInt(MainPanel.HEIGHT/2+30);
    private int width = 78;
    private BufferedImage gold = null;
    public int speed= -8;


    public Gold(int xC){
        super(xC);
        this.xC=xC;
        LoadImage();

    }

    public void LoadImage(){
        try {
            gold = ImageIO.read(new File("images/gold.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void drawObstacle(Graphics g) {
        g.drawImage(gold, xC,yC, null); //bottom

    }
    public void moveObstacle(){
        xC+=speed;
        if(xC<=-width){
            xC=MainPanel.WIDTH;
            yC=rand.nextInt(MainPanel.HEIGHT/2+30);
        }
        Ellipse2D.Double ellipse = new Ellipse2D.Double(xC, yC, 30, 30);
        if(ellipse.intersects(Phoenix.birdArea())){
            golds++;

        }

    }

    public void resetObstacle(){
        yC=rand.nextInt(MainPanel.HEIGHT/2+30);
    }



}
