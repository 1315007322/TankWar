package com.mr.model.wall;

import com.mr.util.ImageUtil;

/**
 * 草地
 */
public class GrassWall extends Wall {

    /**
     * 草地构造方法
     *
     * @param x   初始化 横坐标
     * @param y   初始化 纵坐标
     */
    public GrassWall(int x, int y) {
        super(x, y, ImageUtil.GRASSWALL_IMAGE_URL);
    }
}
