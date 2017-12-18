package com.mbecm.kotlinmap

import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class MainView : Group() {

    val tiles: MutableMap<Int, MutableMap<Long, Image>> = mutableMapOf();

    private var maxXForZoom: Int = 0;
    private var maxYForZoom: Int = 0;

    internal var zoom = 15
        set(value) {
            field = value
            tiles.putIfAbsent(zoom, mutableMapOf())
            System.err.println("zoom: " + value)

            maxXForZoom = 1 shl zoom
            maxYForZoom = 1 shl zoom
            children.clear()
            loadTiles()

        }

    init {
        loadTiles()
    }

    private fun loadTiles() {

        val bounds = boundsInLocal
        val minX = Math.max(0, (-translateX / 256).toInt()).toLong();
        val maxX = Math.min(maxXForZoom, ((-translateX + bounds.width) / 256).toInt()).toLong();

        System.err.println("" + minX + ", " + maxX);

        for (x in minX..maxX) {
//        for (x in 10435L..10440L) {
            for (y in 0L..3L) {
//            for (y in 10435L..10440L) {
                val url = OSM_TILE_URL + zoom + "/" + x + "/" + y + ".png"
                System.err.println(url)

                val img = Image(url, true)
//                tiles.get(zoom)?.put((x + 10) * (y + 20), img);

                val iv = ImageView(img).apply {
                    translateX = 256 * x.toDouble()
                    translateY = 256 * y.toDouble()
                }

                children.add(iv)
            }
        }
    }
}