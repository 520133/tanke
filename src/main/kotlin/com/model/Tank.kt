package com.model

import com.Config
import com.business.Blockable
import com.business.Movable
import com.enums.Direction
import org.itheima.kotlin.game.core.Painter

class Tank(override var x: Int, override var y: Int):Movable{

    override var width: kotlin.Int = Config.block
    override var height: kotlin.Int = Config.block

    override var currentDirection:Direction = Direction.UP
    override var badDirection: Direction? = null

    override var speed:Int = 8

    override fun draw() {
        var image = when (currentDirection) {
            Direction.UP -> "img/tank_u.gif"
            Direction.DOWN -> "img/tank_d.gif"
            Direction.LEFT -> "img/tank_l.gif"
            Direction.RIGHT -> "img/tank_r.gif"
        }
        Painter.drawImage(image,x,y)
    }

    fun move(direction:Direction){

        if(direction != this.currentDirection){
            this.currentDirection = direction
            return
        }
        if (direction == this.badDirection){
            return
        }

        this.currentDirection = direction

        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

        if (x < 0) x = 0
        if (x > Config.gameWidth - width) x = Config.gameWidth - width

        if (y < 0) y = 0
        if (y > Config.gameHeight - height) y = Config.gameHeight - height
    }

    override fun willCollision(block: Blockable): Direction? {
        var x:Int = this.x
        var y:Int = this.y
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
        var collision = when {
            block.y +block.height <= y -> {
                false
            }
            y + height <= block.y -> {
                false
            }
            block.x + block.width <= x -> {
                false
            }
            else -> x + width > block.x
        }



        return if (collision) currentDirection else null
    }


    //不向该方向走，走失败
    override fun notifyCollision(direction: Direction?, block: Blockable?) {
        this.badDirection = direction
    }


}