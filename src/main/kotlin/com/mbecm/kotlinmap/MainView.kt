package com.mbecm.kotlinmap

import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class MainView : Group() {

    val tiles: MutableMap<Int, MutableMap<Long, Image>> = mutableMapOf();

    private var maxXForZoom: Long = 0;
    private var maxYForZoom: Long = 0;

    internal var zoom = 3
        set(value) {
            field = value
            tiles.putIfAbsent(zoom, mutableMapOf())
            System.err.println("zoom: " + value)

            maxXForZoom = 1L shl zoom
            maxYForZoom = 1L shl zoom

            loadTiles()

        }

    init {
        loadTiles()
        style = "-fx-background-color: black; "
    }

    internal fun loadTiles() {
        children.clear()
        children.add(Circle(15.0, Color.BLACK))
        val width: Int = parent?.layoutBounds?.width?.toInt() ?: 0
        val height: Int = parent?.layoutBounds?.height?.toInt() ?: 0
        System.err.println(-translateX)
        val minX = Math.max(0L, Math.abs(-translateX / 256).toLong());
        val maxX = Math.min(maxXForZoom, Math.abs((-translateX + width) / 256).toLong())
//        val maxX = Math.min(maxXForZoom, Math.abs((-translateX + 500) / 256).toInt()).toLong()
        val minY = Math.max(0L, Math.abs(-translateY / 256).toLong())
        val maxY = Math.min(maxYForZoom, Math.abs((-translateY + height) / 256).toLong())

        System.err.println("" + minX + ", " + maxX);

        for (x in minX..maxX) {
            for (y in minY..maxY) {
                val url = OSM_TILE_URL + zoom + "/" + x + "/" + y + ".png"
//                System.err.println(url)

                val img = Image(url, true)
//                tiles.get(zoom)?.put((x + 10) * (y + 20), img);

                val iv = ImageView(img)

                children.add(StackPane(iv, Label("x: " + x + ", y: " + y)).apply {
                    style = "-fx-border-color: black; -fx-border-size:1;"
                    translateX = 256 * x.toDouble()
                    translateY = 256 * y.toDouble()
                })
            }
        }
    }
}