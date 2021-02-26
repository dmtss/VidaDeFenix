import java.awt.*;

    public abstract class Obstacle {

        public int xC;
        public Obstacle(int xC){
         this.xC=xC;

        }


        abstract public void LoadImage();
        abstract public void drawObstacle(Graphics g);
        abstract public void moveObstacle();
        abstract public void resetObstacle();




    }

