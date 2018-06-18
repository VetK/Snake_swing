package all;

import all.Objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainSnake extends JPanel implements ActionListener {

    public static JFrame JFrame;
    public static final int SCALE = 32;         //размер одной клетки в пиксилях
    public static final int WIDTH = 20;         //количество клеток по ширине
    public static final int HEIGHT = 20;        //количество клеток по высоте
    public static int speed = 5;

    Snake s = new Snake(5,5,5,5);
    Timer timer = new Timer(1000/speed, this);

    public MainSnake(){
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint(Graphics g){                        //графический хуожник, который будет рисовать
        g.setColor(Color.black);
        g.fillRect(0,0, SCALE*WIDTH, SCALE*HEIGHT);

        for (int x=0; x<SCALE*WIDTH; x+=SCALE){           //прорисовка вертикальных линий
            g.setColor(Color.white);
            g.drawLine(x,0,x,SCALE*HEIGHT);
        }

        for (int y=0; y<SCALE*HEIGHT; y+=SCALE){          //прорисовка горизонтальных линий
            g.setColor(Color.white);
            g.drawLine(0,y,SCALE*WIDTH,y);
        }


        for (int l = 0; l < s.length; l++){                //прорисовка змейки
            g.setColor(Color.green);
            g.fillRect(s.sX[1]*SCALE+1, s.sY[1]*SCALE+1, SCALE-1, SCALE-1);
        }

    }

    public static void main(String[] args) {
        JFrame = new JFrame("Title");
        JFrame.setSize(SCALE*WIDTH+1, SCALE*HEIGHT+24);
        JFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);     //по закрытию программа будет завершаться
        JFrame.setResizable(false);                                         //делает невозможным растягивание окна
        JFrame.setLocationRelativeTo(null);                                 //место размешения созданного окна
        JFrame.add(new MainSnake());
        JFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        s.move();
        repaint();                                                          //обновление, перерисовка
    }

    public class KeyBoard extends KeyAdapter {
        public void keyPressed(KeyEvent event){
            int key = event.getKeyCode();

            if ((key == KeyEvent.VK_UP) && (s.direction !=2)) s.direction = 0;
            if ((key == KeyEvent.VK_DOWN) && (s.direction !=0)) s.direction = 2;
            if ((key == KeyEvent.VK_LEFT) && (s.direction !=1)) s.direction = 3;
            if ((key == KeyEvent.VK_RIGHT) && (s.direction !=3)) s.direction = 1;

        }

    }

}
