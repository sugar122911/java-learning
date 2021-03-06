package com.sugar.tank;

import com.mashibing.tank.Dir;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 继承Frame,重写paint方法，在画布上画出黑色方块
 */
public class TankFrame extends Frame {

    Tank tank = new Tank(200, 200, Dir.DOWM,this);
    Bullet bullet=new Bullet(300,300,Dir.DOWM,this);
    List<Bullet> bulletList=new ArrayList<Bullet>();

    //高度，宽度
    public static final int GAME_WIDTH=800,GAME_HEIGHT=600;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        //是否能改变大小
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        this.addKeyListener(new MykeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //解决双缓冲问题
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }



    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数目："+bulletList.size(),10,60);
        g.setColor(c);
        tank.paint(g);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
    }

    class MykeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) {
                tank.setMoving(false);
            }else {
                tank.setMoving(true);
                if (bL) tank.setDir(Dir.LEFT);
                if (bU) tank.setDir(Dir.UP);
                if (bR) tank.setDir(Dir.RIGHT);
                if (bD) tank.setDir(Dir.DOWM);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_SPACE:
                    tank.fire();
                default:
                    break;
            }
            setMainTankDir();
        }
    }
}
