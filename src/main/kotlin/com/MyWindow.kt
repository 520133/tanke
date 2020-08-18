package com


import com.business.Blockable
import com.business.Movable
import com.enums.Direction
import com.model.*
import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window
import java.io.File

class MyWindow: Window(title = "坦克大战",
    icon = "img/tank_u.gif",
    width = Config.gameWidth,
    height = Config.gameHeight) {

    private val views = arrayListOf<View>()

    private lateinit var tank:Tank
    
    //创建窗口
    override fun onCreate() {
        var file = File(javaClass.getResource("/map/1.map").path)
        var readLines = file.readLines()
        var lineNum = 0
        readLines.forEach{
            var columNum = 0
            var charArray = it.toCharArray().forEach {
                    columu->
                when (columu){
                    '砖' -> views.add(Wall(columNum * Config.block,lineNum * Config.block))
                    '草' -> views.add(Grass(columNum * Config.block,lineNum * Config.block))
                    '水' -> views.add(Water(columNum * Config.block,lineNum * Config.block))
                    '铁' -> views.add(Steel(columNum * Config.block,lineNum * Config.block))
                }
                columNum ++
            }
            lineNum ++
        }
        //绘制坦克
        tank = Tank(Config.block*10,Config.block*12)

        views.add(tank)
    }

    //窗体渲染时的回调
    override fun onDisplay() {
        //绘制地图
        views.forEach{
            it.draw()
        }

        //绘制颜色
        //Painter.drawColor(Color.RED,80,0,100,100)

        //绘制字体
        //Painter.drawText("杨杰",100,50)
    }

    //按键响应时间
    override fun onKeyPressed(event: KeyEvent) {
        when(event.code){
            KeyCode.ENTER -> println("回车事件")
            KeyCode.W -> {
                tank.move(Direction.UP)
            }
            KeyCode.S -> {
                tank.move(Direction.DOWN)
            }
            KeyCode.A -> {
                tank.move(Direction.LEFT)
            }
            KeyCode.D -> {
                tank.move(Direction.RIGHT)
            }
        }
    }

    //刷新
    override fun onRefresh() {

        //判断运动物体和阻塞物体是否发送碰撞

        //1》找到运动物体
        views.filter { it is Movable }.forEach {mova ->
            //2》找到阻塞物体

            mova as Movable//类似java强转类型
            var badDirection:Direction? = null
            var badBloc:Blockable? = null
            views.filter { it is Blockable }.forEach blockable@{ block ->

                block as Blockable

                var direction = mova.willCollision(block)

                direction?.let {
                    //当前循环添加了标记，此处跳出添加标记的循环
                    badDirection = direction
                    badBloc = block
                    return@blockable
                }
            }

            mova.notifyCollision(badDirection,badBloc)
        }


    }


}

fun main(args: Array<String>) {
    Application.launch(MyWindow::class.java)
}