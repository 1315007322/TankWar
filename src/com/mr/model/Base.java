package com.mr.model;

import com.mr.model.wall.Wall;
import com.mr.util.ImageUtil;

public class Base extends Wall {



    /**
     * 墙体构造方法
     *
     * @param x   初始化 横坐标
     * @param y   初始化 纵坐标
     * @param url 初始化 图片的路径
     */
    public Base(int x, int y, String url) {
        super(x, y, ImageUtil.BASE_IMAGE_URL); //调用父类构造方法 使用默认基地图片
    }


}
