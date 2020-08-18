package com.model

import com.Config
import org.itheima.kotlin.game.core.Painter

/**
 * 草坪
 */
class Grass(override var x: Int, override var y: Int):View{
    override var width: Int = Config.block
    override var height: Int = Config.block
    override fun draw() {
        Painter.drawImage("img/grass.gif",x,y)
    }
    //
}