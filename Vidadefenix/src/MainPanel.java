import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;



public class MainPanel extends JPanel {
    public static final long serialVersionUID=1;
    public static final int WIDTH = 700;
    public static final int HEIGHT = 850;
    public static  int a, b,finalscore;
    private Font customFont;

    Color color = new Color(155, 1, 255);
    Color goldc = new Color(255,234,125);

    private int x = 0;
    private BufferedImage img=null;
    Phoenix phoenix = new Phoenix();
    Obstacle pipes = new Pipes(MainPanel.WIDTH);
    Obstacle pipes1 = new Pipes(MainPanel.WIDTH+(MainPanel.WIDTH/2)+55);
    Obstacle ball = new Fireball(MainPanel.WIDTH+(MainPanel.WIDTH/4));
    Obstacle ball1 = new Fireball(MainPanel.WIDTH+3*(MainPanel.WIDTH/4)+150);
    Obstacle gold = new Gold(MainPanel.WIDTH+250);
    Obstacle gold2 = new Gold(MainPanel.WIDTH+600);
    public static boolean GameOver = false;
    public static int secondspassed=0,min,max;
    public static Timer mytimer = new Timer();
         TimerTask task = new TimerTask(){
    public void run(){
         secondspassed++;
    }

    };



public  void start() {
    mytimer.scheduleAtFixedRate(task, 1000, 1000);

}


    public MainPanel() {
        LoadImage();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                phoenix.jump();

            }
        });


    }



    public void LoadImage() {
        try {
            img = ImageIO.read(new File("images/bground.png"));

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, x, 0, null);
        phoenix.drawFenix(g);
        pipes.drawObstacle(g);
        pipes1.drawObstacle(g);
        gold.drawObstacle(g);
        gold2.drawObstacle(g);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("BAKUSHO.ttf")).deriveFont(35f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }

        g.setFont(customFont);
        g.setColor(color);
        g.drawString("TIME " + secondspassed, 50, 70);
        g.setColor(goldc);
        g.drawString("GOLD " + Gold.golds, 50, 100);

        ball.drawObstacle(g);
        ball1.drawObstacle(g);
        try {
            File fas = new File ("scores.txt");
            Scanner sc = new Scanner(fas);
            max= sc.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(color);
        g.drawString("HIGHEST SCORE " + max, 480, 70);


    }
    public void move(){
        phoenix.birdPlacement();
        pipes.moveObstacle();
        pipes1.moveObstacle();
        ball.moveObstacle();
        ball1.moveObstacle();
        gold.moveObstacle();
        gold2.moveObstacle();



        if(GameOver){
            pipes = new Pipes(MainPanel.WIDTH);
            pipes1= new Pipes(MainPanel.WIDTH+(MainPanel.WIDTH/2)+55);
            ball = new Fireball(MainPanel.WIDTH+(MainPanel.WIDTH/4));
            ball1= new Fireball(MainPanel.WIDTH+3*(MainPanel.WIDTH/4)+150);
            gold = new Gold(MainPanel.WIDTH+250);
            gold2 = new Gold(MainPanel.WIDTH+600);
            Gold.golds=0;
            GameOver=false;


        }



    }


    public static void playMusic(String musicLocation){
        try{
            File musicpath=new File(musicLocation);
            if(musicpath.exists()){
                AudioInputStream audioInput= AudioSystem.getAudioInputStream(musicpath);
                Clip clip= AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

            }
            else{

            }

        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public static boolean gameOverMsg()   {
        a=secondspassed;
        b= Gold.golds;
        finalscore= a+b/2;
        try {
            File fas = new File ("scores.txt");
            Scanner sc = new Scanner(fas);
            min= sc.nextInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int res= JOptionPane.showConfirmDialog(null,"You died... You survived for "+secondspassed+" second(s). \n Your final score is: " +finalscore+ "\n Try again?","Game Over", JOptionPane.YES_NO_OPTION);

         if(min<finalscore) {
            try {
                File fac = new File("scores.txt");
                String s = Integer.toString(finalscore);
                FileWriter wr = new FileWriter(fac);
                wr.write(s);
                wr.close();
                max = finalscore;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if(res==JOptionPane.YES_OPTION){

            return true;

        }
        else {

            return false;
        }


    }


}