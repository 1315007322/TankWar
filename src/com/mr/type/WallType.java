package com.mr.type;

/**
 * 定义墙体
 * 砖墙：可以阻挡坦克前进，被子弹击中后会同子弹一起消失 birck
 * 草地：不会阻挡坦克和子弹前进，但会遮挡坦克 grass
 * 河流：可以阻挡坦克前进，但是不会阻挡子弹前进 river
 * 铁墙：可以阻挡坦克和子弹 iron
 * 基地：玩家守护的重要对象，可以阻挡坦克前进，但是被子弹击中后则游戏失败 base
 */
public enum WallType {
    brick,
    grass,
    river,
    iron,
    base,
}
