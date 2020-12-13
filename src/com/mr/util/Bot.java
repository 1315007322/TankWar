package com.mr.util;

import com.mr.farme.GamePanel;
import com.mr.model.Tank;
import com.mr.type.Direction;
import com.mr.type.TankType;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Bot extends Tank {
//  随机类
    private Random random = new Random();
//  移动方向
    private Direction direction;
//  刷新时间 采用游戏面板的刷新时间
    private int fresh = GamePanel.FRESH;
//    移动计时器
    private int MoveTimer = 0;


    /**
     * 构造电脑坦克
     * @param x 横坐标
     * @param y 纵坐标
     * @param url 图片路径
     * @param gamePanel 游戏面板
     * @param type 坦克类型
     */
    public Bot(int x, int y, String url, GamePanel gamePanel, TankType type) {
        super(x, y, ImageUtil.BOOM_IMAGE_URL, gamePanel, type);
        direction = Direction.DOWN;//移动方向默认向下
        setAttackCoolDownTime(1000);//设置攻击冷却时间
    }

    /**
     * 为电脑 玩家 随机方向
     * @return 为电脑玩家 随机方向
     */
    private Direction randomDirection(){
        int rnum = random.nextInt(4); //随机数 0-3
        switch (rnum){
            case 0:
                return Direction.RIGHT;
            case 1:
                return Direction.LEFT;
            case 2:
                return Direction.UP;
            default:
                return Direction.DOWN;
        }
    }

    /**
     * 重写 父类碰撞 堵车 逻辑
     * @param x 横坐标
     * @param y 纵坐标
     * @return
     */
    boolean hitTank(int x,int y){
        Rectangle next = new Rectangle(x,y,width,height); //创建碰撞位置
        List<Tank> tanks =  gamePanel.getTanks(); // 获取所有坦克集合
        for(int i = 0;i<tanks.size();i++){ //遍历坦克集合
            Tank t = tanks.get(i);// 获取坦克对象
            if(!this.equals(t)){ //如果该坦克 不是本身
                if(t.isAlive() && t.hit(next)){  //如果存活
                    if(t instanceof Bot){ //如果也是电脑
                        direction = randomDirection(); //随机方向 调整
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 攻击
     */
    public void attack(){
        int rnum = random.nextInt(100);
        if(rnum < 4){
            super.attack();
        }
    }

    public Bot(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Bot(int x, int y, String url) {
        super(x, y, url);
    }
}
