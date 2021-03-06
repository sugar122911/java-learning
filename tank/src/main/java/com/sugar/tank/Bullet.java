package com.sugar.tank;

import com.mashibing.tank.Dir;

import java.awt.*;

/**
 * 子弹类
 */
public class Bullet {

    //速度
    private static final int SPEED = 10;

    //高度，宽度
    private static final int WIDTH = 5, HEIGHT = 5;

    private int x, y;
    //方向
    private Dir dir;


    private boolean isAlive = true;

    private TankFrame tankFrame;

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }


    /**
     * 画出子弹，设置属性
     *
     * @param g
     */
    public void paint(Graphics g) {
        if(!isAlive){
            tankFrame.bulletList.remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
        move();
    }

    /**
     * 移动方向
     */
    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWM:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            isAlive = false;
        }
    }
}
