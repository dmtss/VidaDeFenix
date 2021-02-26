import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GFrame implements ActionListener {

    protected static JFrame game;
    protected static Timer timer;

    private Graphics g;
    Phoenix phoenix = new Phoenix();
    Menu m= new Menu();
    MainPanel p = new MainPanel();
    ImageIcon img = new ImageIcon("images/fenix.png");



    public GFrame(){

        game= new JFrame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(700,850);
        game.setLocationRelativeTo(null);
        game.setIconImage(img.getImage());
        game.setTitle("Vida de Fénix");
        game.setResizable(true);

    }

    private void Start(){
        timer=new Timer(30, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                p.repaint();
                p.move();

            }
        });
        game.add(m);
        game.setVisible(true);
        while(m.isStarted==false){
        try{
            Thread.sleep(10);

        } catch(Exception e){
            e.printStackTrace();

        }
         }
        game.remove(m);
        game.add(p);
        p.setVisible(true);
        game.revalidate();
        timer.start();
        phoenix.LoadImage();
        p.start();


        }

    @Override
    public void actionPerformed(ActionEvent e) {
        p.repaint();
    }
    public static void main (String[] args){
       GFrame GFrame = new GFrame();
       GFrame.Start();
       String filepath="Tarantella.wav";
        MainPanel.playMusic(filepath);

    }





}