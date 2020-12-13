package com.mr.model;

import com.mr.farme.GamePanel;
import com.mr.model.wall.GrassWall;
import com.mr.model.wall.Wall;
import com.mr.type.Direction;
import com.mr.type.TankType;
import com.mr.util.ImageUtil;
import com.mr.util.VisibleImage;

import java.awt.*;
import java.util.List;

public class Tank extends VisibleImage {

    protected GamePanel gamePanel;    //游戏面板
    Direction direction;   // 移动方向
    protected boolean alive  = true;// 是否存活
    protected int speed = 3;//移动速度
    private boolean attackCoolDown = true;// 攻击冷却状态
    private int attackCoolDownTime = 500;// 攻击冷却时间，ms
    TankType type;// 坦克类型
    private String upImage;// 向上移动的图片
    private String downImage;// 向下移动的图片
    private String rightImage;// 向右移动的图片
    private String leftImage;// 向左移动的图片

    /**
     *坦克构造方法
     * @param x 初始化横坐标
     * @param y 初始化纵坐标
     * @param url 图片路径
     * @param gamePanel 游戏面板
     * @param type 坦克；类型
     */
    public Tank(int x, int y, String url, GamePanel gamePanel, TankType type){
        super(x,y,url);
        this.gamePanel = gamePanel;
        this.type = type; //初始化坦克类型 玩家一、玩家二、电脑
        direction = Direction.UP; //初始化方向向上
        switch (type){
            case player1:  //玩家一的四个方向图片
                upImage = ImageUtil.PLAYER1_UP_IMAGE_URL;
                downImage = ImageUtil.PLAYER1_DOWN_IMAGE_URL;
                leftImage = ImageUtil.PLAYER1_LEFT_IMAGE_URL;
                rightImage = ImageUtil.PLAYER1_RIGHT_IMAGE_URL;
                break;
            case player2: //玩家二的四个方向图片
                upImage = ImageUtil.PLAYER2_UP_IMAGE_URL;
                downImage = ImageUtil.PLAYER2_DOWN_IMAGE_URL;
                leftImage = ImageUtil.PLAYER2_LEFT_IMAGE_URL;
                rightImage = ImageUtil.PLAYER2_RIGHT_IMAGE_URL;
                break;
            case bot://电脑一的四个方向图片
                upImage = ImageUtil.BOT_UP_IMAGE_URL;
                downImage = ImageUtil.BOT_DOWN_IMAGE_URL;
                leftImage = ImageUtil.BOT_LEFT_IMAGE_URL;
                rightImage = ImageUtil.BOT_RIGHT_IMAGE_URL;
                break;
        }

    }


    /**
     * 判断坦克碰撞到墙体
     * @return 判断坦克碰撞到墙体
     */
    private boolean hitWall(int x,int y){
        Rectangle next = new Rectangle(x,y,width,height);//创建坦克移动后的区域
        List<Wall> walls = gamePanel.getWalls(); //获取所有墙块
        for(int i = 0;i<walls.size();i++){// 遍历所有墙块
            Wall w = walls.get(i);// 获取墙块对象
            if(w instanceof GrassWall){// 判断是否为草地
                continue;//  如果是草地 执行下一此循环
            }else if(w.hit(next)){// 如果碰撞到墙体
                return true;// 返回 碰撞到墙体
            }
        }
        return false;
    }

    /**
     * 判断是否 碰撞其他坦克
     * @param x 横坐标
     * @param y  纵坐标
     * @return 判断是碰撞其他坦克
     */
    boolean hitTank(int x, int y){
        Rectangle next = new Rectangle(x,y,width,height);//创建坦克移动后的区域
        List<Tank> tanks = gamePanel.getTanks(); //获取所有坦克
        for(int i = 0;i<tanks.size();i++){// 遍历所有坦克
            Tank t = tanks.get(i);// 获取坦克对象
            if(!this.equals(t)){ //如果此时坦克与自身不是同一个 对象元素
                if(t.isAlive() && t.hit(next)){
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断 坦克是否超越 游戏边界
     */
    protected void moveToBorder(){
         if(x < 0){
             x = 0;
         }else if(x > gamePanel.getWidth() - width){
             x = gamePanel.getWidth() - width;
         }
         if(y < 0 ){
             y = 0;
         }else if(y > gamePanel.getHeight() - height){
             y = gamePanel.getHeight() - height;
         }
    }


//    坦克 向左移动
    public void leftward(){
        if(direction != Direction.LEFT){
            setImage(leftImage);
        }
        direction = Direction.LEFT;
        if(!hitWall(x - speed,y) && !hitTank(x - speed,y)){
            x -= speed;
            moveToBorder();
        }
    }

//    坦克向右 移动
    public void rightward(){
        if(direction != Direction.RIGHT){ //如果和之前的操作的方向 和现在方向不一致
            setImage(rightImage); //变为 操作的 方向 的图片
        }
        direction = Direction.RIGHT; //移动方向  设置为当前操作方向
        if(!hitWall(x+speed,y) && !hitTank(x+speed,y)){ //判断 移动后 是否会碰到边界
            x += speed; // 横坐标递增
            moveToBorder(); //判断是否移动到面板 的边界
        }
    }

    //    坦克向上 移动
    public void upward(){
        if(direction != Direction.UP){ //如果和之前的操作的方向 和现在方向不一致
            setImage(upImage); //变为 操作的 方向 的图片
        }
        direction = Direction.UP; //移动方向  设置为当前操作方向
        if(!hitWall(x,y - speed) && !hitTank(x,y - speed)){ //判断 移动后 是否会碰到边界
            y -= speed; // 纵坐标坐标递减
            moveToBorder(); //判断是否移动到面板 的边界
        }
    }

    //    坦克向下 移动
    public void downward(){
        if(direction != Direction.DOWN){ //如果和之前的操作的方向 和现在方向不一致
            setImage(downImage); //变为 操作的 方向 的图片
        }
        direction = Direction.DOWN; //移动方向  设置为当前操作方向
        if(!hitWall(x,y + speed) && !hitTank(x,y + speed)){ //判断 移动后 是否会碰到边界
            y += speed; // 纵坐标 递增
            moveToBorder(); //判断是否移动到面板 的边界
        }
    }

    /**
     * 攻击冷却CD 线程类
     */
    public class AttackCD extends Thread{
        public void run(){   //线程主方法
            attackCoolDown = false;  //将攻击功能设为冷却状态
            try {
                Thread.sleep(attackCoolDownTime);  // 休眠0.5秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            attackCoolDown = true;  // 将攻击功能解除 冷却时间
        }
    }

    /**
     * 攻击发射子弹
     */
    public void attack(){
        if(attackCoolDown){
            Point p = getHeadPoint(); //如果攻击功能完成冷却
            //在坦克头位置发射与坦克角度相同的子弹
            Bullet b = new Bullet(p.x - Bullet.LENGTH /2,p.y - Bullet.LENGTH /2,direction,gamePanel,type);
            gamePanel.addBullet(b);
            new AttackCD().start();
        }
    }

    /**
     * 获取坦克头点
     *
     * @return 头点对象
     */
    private Point getHeadPoint() {
        Point p = new Point();// 创建点对象，作为头点
        switch (direction) {// 判断移动方向
            case UP:// 如果向上
                p.x = x + width / 2;// 头点横坐标取x加宽度的一般
                p.y = y;// 头点纵坐标取y
                break;
            case DOWN:// 如果向下
                p.x = x + width / 2;// 头点横坐标取x加宽度的一般
                p.y = y + height;// 头点纵坐标取y加高度的一般
                break;
            case RIGHT:// 如果向右
                p.x = x + width;// 头点横坐标取x加宽度的一般
                p.y = y + height / 2;// 头点纵坐标取y加高度的一般
                break;
            case LEFT:// 如果向左
                p.x = x;// 头点横坐标取x
                p.y = y + height / 2;// 头点纵坐标取y加高度的一般
                break;
            default:// 默认
                p = null;// 头点为null
        }
        return p;// 返回头点
    }



//    坦克向上 移动
//    坦克向下 移动
    /**
     * 坦克是否存活
     *
     * @return 存活状态
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * 设置存活状态
     *
     * @param alive
     *            - 存活状态
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * 设置攻击冷却时间
     * @param attackCoolDownTime 设置攻击冷却时间
     */
    public void setAttackCoolDownTime(int attackCoolDownTime){
        this.attackCoolDownTime = attackCoolDownTime;
    }

    public Tank(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Tank(int x, int y, String url) {
        super(x, y, url);
    }
}
