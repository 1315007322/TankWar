package com.mr.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 定义所有模型的  父类---可显示的图像抽象类
 */
public class VisibleImage {
    public int x;    //图像横坐标
    public int y;   //图像的纵坐标
    protected int width;      //图像的宽
    protected int height;     //图像的高度
    protected BufferedImage image; // 图像对象

    /**
     * 构造方法
     * @param x 横坐标
     * @param y 纵坐标
     * @param width 宽度
     * @param height 高度
     */
    public VisibleImage(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //实例化图片
       image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
    }

    /**
     * 构造方法
     * @param x 横坐标
     * @param y 纵坐标
     * @param url 图片路径
     */
    public VisibleImage(int x, int y,String url) {
        this.x = x; // 横坐标
        this.y = y; // 纵坐标
        try {
            image = ImageIO.read(new File(url)); //获取此路径的图片对象
            this.width = image.getWidth(); // 宽为图片宽
            this.height = image.getHeight(); //高为图片高
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    设置图片，参数为 图片文件路径
    public void setImage(String url){
        try {
            this.image = ImageIO.read(new File(url)); //读取指定位置的图片
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    获取边界
    public Rectangle getBounds(){
//        创建一个坐标在(x,y)位置，宽、高为(Width,height)的矩形边界对象并返回
        return new Rectangle(x,y,width,height);
    }

//    判断两个图片是否碰撞
    public boolean hit(Rectangle r){
        if(r == null){
            return false;
        }
        return getBounds().intersects(r);
    }

//    获取图像的宽
    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    //获取图像的高
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
