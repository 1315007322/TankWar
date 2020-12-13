package com.mr.farme;

import com.mr.model.Bullet;
import com.mr.model.Tank;
import com.mr.model.wall.Wall;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {

//    游戏界面刷新时间 20ms
    public static final int FRESH = 20;
    private List<Bullet> bullets;//获取所有子弹集合
    private List<Tank> allTanks; //获取所有坦克
    private List<Wall> walls; //所有墙块
    private volatile boolean finish = false; //游戏是否结束
//    获取所有墙
    public List<Wall> getWalls(){
        return walls;
    }

//    获取坦克
    public List<Tank> getTanks(){
        return allTanks;
    }

    public class FreshThread extends Thread{
        public void run(){//线程主方法
            while (!finish){//如果游戏未停止
                repaint(); //执行本类重绘方法
                try{
                    Thread.sleep(FRESH);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 向子弹集合中添加子弹
     * @param b 向子弹集合中添加子弹
     */
    public void addBullet(Bullet b){
        bullets.add(b);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
