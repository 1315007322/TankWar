package com.mr.model.wall;

import com.mr.util.VisibleImage;

public abstract class Wall extends VisibleImage {
    private boolean alive = true; //墙体是否存活  有效


    /**
     *墙体构造方法
     * @param x 初始化 横坐标
     * @param y  初始化 纵坐标
     * @param url 初始化 图片的路径
     */
    public Wall(int x, int y, String url) {
        super(x, y, url); //调用父类 构造方法
    }

    /**
     * 返回是否有效
     * @return 返回是否有效
     */
    public boolean isAlive(){
        return alive;
    }

    /**
     * 设置是否有效
     * @param alive 设置是否有效
     */
    public void setAlive(boolean alive){
        this.alive  = alive;
    }

    /**
     * 重写判断方法，如果两个墙块的坐标相同，则认为两个墙块是同一个墙体
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Wall){  //如果传入的对象 是墙块 或者是墙块的 子类
            Wall w = (Wall) obj; // 则强制装换为墙体的对象
            if(w.x == x && w.y == y){//如果墙体的对象 x 和 y 相等
                return true; //那么 就判断为 为同一个墙体
            }
        }
        return super.equals(obj);  //返回 父类的方法
    }
}
