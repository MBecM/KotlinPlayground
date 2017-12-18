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

class MainView : Group() {

    val tiles: MutableMap<Int, MutableMap<Long, Image>> = mutableMapOf();

    private var maxXForZoom: Int = 0;
    private var maxYForZoom: Int = 0;

    internal var zoom = 3
        set(value) {
            field = value
            tiles.putIfAbsent(zoom, mutableMapOf())
            System.err.println("zoom: " + value)

            maxXForZoom = 1 shl zoom
            maxYForZoom = 1 shl zoom

            loadTiles()

        }

    init {
        loadTiles()
    }

    internal fun loadTiles() {
        children.clear()
        val width :Int = parent?.layoutBounds?.width?.toInt() ?: 0
        val height :Int = parent?.layoutBounds?.height?.toInt() ?: 0
        System.err.println(-translateX)
        val minX = Math.max(0, Math.abs(-translateX / 256).toInt()).toLong();
        val maxX = Math.min(maxXForZoom, minX.toInt() + width).toLong();
//        val maxX = Math.min(maxXForZoom, Math.abs((-translateX + 500) / 256).toInt()).toLong();
        val minY = Math.max(0, (-translateY / 256).toInt()).toLong();
        val maxY = Math.min(maxYForZoom, minY.toInt() + height).toLong();

        System.err.println("" + minX + ", " + maxX);

        for (x in minX..maxX) {
//        for (x in 10435L..10440L) {
            for (y in minY..maxY) {
//            for (y in 10435L..10440L) {
                val url = OSM_TILE_URL + zoom + "/" + x + "/" + y + ".png"
//                System.err.println(url)

                val img = Image(url, true)
//                tiles.get(zoom)?.put((x + 10) * (y + 20), img);

                val iv = ImageView(img).apply {
                    style = "-fx-border-color: black; -fx-border-size:1;"
//                    translateX = 256 * x.toDouble()
//                    translateY = 256 * y.toDouble()
                }

                children.add(StackPane(iv, Label("x: " + x + ", y: " + y)).apply {
                    translateX = 256 * x.toDouble()
                    translateY = 256 * y.toDouble()
                })
//                children.add(iv)
            }
        }
    }
}