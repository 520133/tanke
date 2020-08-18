package com.model

import com.Config
import com.business.Blockable
import org.itheima.kotlin.game.core.Painter

/**
 * 泥巴
 */
class Wall(override var x: Int, override var y: Int) : Blockable {

    override var width: Int = Config.block
    override var height: Int = Config.block
    override fun draw() {
        Painter.drawImage("img/wall.gif",x,y)
    }

}