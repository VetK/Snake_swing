package all.Objects;

import all.MainSnake;

public class Apple {
    public int posX;
    public int posY;

    public Apple (int x, int y){
        posX = x;
        posY = y;
    }

    public void setRandomPosition(){

        posX = Math.abs((int) (Math.random()*MainSnake.WIDTH-1));
        posY = Math.abs((int) (Math.random()*MainSnake.HEIGHT-1));
    }
}
