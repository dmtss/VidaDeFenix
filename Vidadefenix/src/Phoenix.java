import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Phoenix {

    public static final int D = 30;
    public static int x=230,y=300;
    private Image bird;
    private static int speed=2;
    private int gravity = 1;

    public Phoenix(){
        LoadImage();
        }


    public void drawFenix(Graphics g) {
        g.drawImage(bird, x,y, null);
    }
    public void LoadImage(){
        try {
            bird = ImageIO.read(new File("images/fenix.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }
    public void birdPlacement() {
        if(y>=0 && y<=MainPanel.HEIGHT){
            speed += gravity;
            y+=speed;
        }
        else{

            boolean choose = MainPanel.gameOverMsg();
            if(choose){
                try{
                    Thread.sleep(500);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                reset();
                MainPanel mp3 = new MainPanel();
                mp3.secondspassed=0;
            }
            else{
                System.exit(0);
                GFrame.timer.stop();

            }

        }
    }

    public static void reset() {
        x=230;
        y=300;
        speed=2;
        MainPanel.GameOver=true;
    }

    public void jump(){
        speed = -15;

    }
    public static Rectangle birdArea(){
        Rectangle fenix = new Rectangle(x,y,D,35);
        return fenix;
    }

    }

