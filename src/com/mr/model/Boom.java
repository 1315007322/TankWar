package com.mr.model;

import com.mr.farme.GamePanel;
import com.mr.util.ImageUtil;
import com.mr.util.VisibleImage;

import java.awt.*;

public class Boom extends VisibleImage {


    //计时器

    private int timer = 0;
    //刷新时间
    private int fresh = GamePanel.FRESH;

    //是否存活

    private boolean alive = true;


    /**
     * 爆炸 效果的关键方法 是show（）方法  爆炸方法 持续 0.5秒 如果爆炸效果 存活状态
     *
     * @param g2
     */
    public void show(Graphics2D g2){
        if(timer >= 500){
            alive = false;
        }else {
            g2.drawImage(getImage,x,y,null);
            timer += fresh;
        }
    }


    public Boom(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    //调用 父类构造方法 使用默认 爆炸效果时间 效果图片
    public Boom(int x, int y, String url) {
        super(x, y, ImageUtil.BOOM_IMAGE_URL);
    }
}
