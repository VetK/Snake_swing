package all;

import all.Objects.Apple;
import all.Objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainSnake extends JPanel implements ActionListener {

    public static JFrame JFrame;
    public static final int SCALE = 32;         //размер одной клетки в пикселях
    public static final int WIDTH = 20;         //количество клеток по ширине
    public static final int HEIGHT = 20;        //количество клеток по высоте
    public static int speed = 10;

    Snake s = new Snake(5,5,5,5);
    Apple apple = new Apple(Math.abs((int) (Math.random()*MainSnake.WIDTH-1)), Math.abs((int) (Math.random()*MainSnake.HEIGHT-1)));

    Timer timer = new Timer(1000/speed, this);

    public MainSnake(){
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint(Graphics g){                        //графический хуожник, который будет рисовать
        g.setColor(Color.white);
        g.fillRect(0,0, SCALE*WIDTH+2, SCALE*HEIGHT+2);

        g.setColor(Color.red);                             //прорисовка яблока
        g.fillOval(apple.posX*SCALE+3, apple.posY*SCALE+3, SCALE-6, SCALE-6);

        for (int l = 1; l < s.length; l++){                //прорисовка змейки
            g.setColor(Color.green);
            g.fillRect(s.sX[l]*SCALE+3, s.sY[l]*SCALE+3, SCALE-6, SCALE-6);

            g.setColor(Color.orange);
            g.fillOval(s.sX[0]*SCALE+3, s.sY[0]*SCALE+3, SCALE-6, SCALE-6);
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
        if((s.sX[0] == apple.posX) && (s.sY[0] == apple.posY)){             //увеличиваем длину змейки при съедании точки
            apple.setRandomPosition();
            s.length++;
        }

        for (int l = 1; l < s.length; l++){                                 //делаем чтобы точка не появлялась на теле змейки
            if ((s.sX[l] == apple.posX) && (s.sY[l] == apple.posY))
                apple.setRandomPosition();
        }

        for (int l = 1; l < s.length; l++){                                 //при врезании змейки в себя - игра останавливаеться
            if ((s.sX[0] == s.sX[l]) && (s.sY[0] == s.sY[l])) {
                JOptionPane.showMessageDialog(null, "You are los");
                s.length = 2;
                s.direction = 0;
                apple.setRandomPosition();
            }
        }
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