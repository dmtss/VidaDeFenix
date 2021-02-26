import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;
import java.util.Random;

public class Fireball extends Obstacle {

    private Random rand = new Random();
    public int xC, yC=rand.nextInt(MainPanel.HEIGHT/2+30);
    private int width = 40;
    private BufferedImage ball = null;
    public int speed= -13;



    public Fireball(int xC){
        super(xC);
        this.xC=xC;
        LoadImage();
    }

    public void LoadImage(){
        try {
            ball = ImageIO.read(new File("images/mor.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void drawObstacle(Graphics g) {
        g.drawImage(ball, xC,yC, null); //bottom

    }

    public void moveObstacle(){
        xC+=speed;
        if(xC<=-width){
            xC=MainPanel.WIDTH+MainPanel.WIDTH/4;
            yC=rand.nextInt(MainPanel.HEIGHT/2+30);
        }
        Rectangle fire = new Rectangle(xC,yC,width,40);
        if(fire.intersects(Phoenix.birdArea())){
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
                MainPanel mp2= new MainPanel();
                mp2.secondspassed=0;

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

