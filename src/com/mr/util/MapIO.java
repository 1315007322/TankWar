package com.mr.util;

import com.mr.type.WallType;

import java.io.File;
import java.util.List;

/**
 * 此类记录了地图相关的文件存放地址以及属性
 * 提供解析地图数据文件的功能，将地图文件中的坐标数据解析成具体的墙块对象，供游戏面板调用
 */
public class MapIO {
//    地图数据文件存放路径
    public final static String DATA_PATH = "map/data/";
//    地图预览图路径
    public final static String IMAGE_PATH = "map/image/";
//    地图数据文件后缀
    public final static String DATA_SUFFIX = ".map";
//    地图预览图后缀
    public final static String IMAGE_SUFFIX = ".jpg";

    /**
     *获取地图文件
     * @param mapName
     * @return
     */
//    public static List<WALL> readMap(File mapName){
////        创建对应名称的地图文件
//        File file = new File(DATA_PATH + mapName + DATA_SUFFIX);
//        return readMap(file); //调用重载方法
//    }

}
