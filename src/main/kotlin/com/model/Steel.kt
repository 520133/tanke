package com.model

import com.Config
import org.itheima.kotlin.game.core.Painter

/**
 * 铁墙
 */
class Steel(override var x: Int, override var y: Int):View{

    override var width: Int = Config.block
    override var height: Int = Config.block
    override fun draw() {
        Painter.drawImage("img/steel.gif",x,y)
    }

}