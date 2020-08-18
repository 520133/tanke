package com.business

import com.enums.Direction
import com.model.View

/**
 * 运动物体
 */
interface Movable:View {

    //需要具备方向
    var currentDirection:Direction
    //禁止移动的方向
    var badDirection:Direction?

    //需要具备移动速度
    var speed:Int

    fun willCollision(block:Blockable):Direction?
    fun notifyCollision(direction: Direction?,block:Blockable?)

}