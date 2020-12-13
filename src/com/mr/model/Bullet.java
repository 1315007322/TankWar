package com.mr.model;

import com.mr.farme.GamePanel;
import com.mr.type.Direction;
import com.mr.type.TankType;
import com.mr.util.VisibleImage;

import java.awt.*;

/**
 *子弹
 *
 */
public class Bullet extends VisibleImage {

    Direction direction; // 移动方向
    static final int LENGTH = 8; //子弹的(正方体)边长
    private GamePanel gamePanel; //游戏面板
    private int speed = 7; //移动速度
    private boolean alive = true;//子弹是否存活 有效
    Color color = Color.ORANGE;//子弹颜色为橙色
    TankType owner;//发出子弹的坦克类型

    /**
     * 绘制子弹的形状 初始化方法
     */
    public void init(){
        Graphics g = image.getGraphics(); //获取图片的绘图的方法
        g.setColor(Color.WHITE);//使用白色绘图
        g.fillRect(0,0,LENGTH,LENGTH); //绘制一个铺满整个图片的白色实心矩形
        g.setColor(color);//使用子弹颜色
        g.fillRect(0,0,LENGTH,LENGTH);//绘制一个铺满整个图片的实心圆形
        g.setColor(Color.BLACK);//使用黑色
        //给图形绘制一个黑色的边框，防止绘出界，宽高各减少1像素
        g.drawOval(0,0,LENGTH - 1,LENGTH - 1);
    }


    /**
     * 构建子弹 构造方法
     * @param x 横坐标
     * @param y 纵坐标
     * @param direction 方向
     * @param gamePanel 游戏面板
     * @param owner 坦克类型
     */
    public Bullet(int x, int y, Direction direction,GamePanel gamePanel,TankType owner) {
        super(x, y,LENGTH,LENGTH);
        this.direction = direction;
        this.gamePanel = gamePanel;
        this.owner = owner;
        init();
    }

    /**
     * 子弹移出边界则销毁
     */
    public void moveToBorder(){
//          判断子弹出界
        if(x < 0 || x > gamePanel.getWidth() - getWidth() || y < 0 || y > gamePanel.getHeight() - getHeight() ){

        }
    }

//    向左移动
    public void leftward (){
        x -= speed;
        moveToBorder();
    }
    //    向右移动
    public void rightward (){
        x += speed;
        moveToBorder();
    }
    //    向下移动
    public void downward (){
        y += speed;
        moveToBorder();
    }
    //    向上移动
    public void upward (){
        y -= speed;
        moveToBorder();
    }

    /**
     * 子弹发出时的方向 自动识别 当前方向
     */
    public void move(){
        switch (direction){
            case UP:
                upward();
                break;
            case DOWN:
                downward();
                break;
            case LEFT:
                leftward();
                break;
            case RIGHT:
                rightward();
                break;
        }
    }



    public Bullet(int x, int y, String url) {
        super(x, y, url);
    }

    /**
     * 销毁子弹
     */
    private synchronized void dispose(){
        alive = false;
    }

    /**
     * 获取子弹状态
     * @return 获取子弹状态
     */
    private boolean isAlive(){
        return alive;
    }
}
