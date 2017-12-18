package com.mbecm.kotlinmap

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import java.io.File
import java.nio.file.Files

class Main : Application() {
    override fun start(primaryStage: Stage?) {
        primaryStage?.apply {
            scene = Scene(BaseMap(MainView()), 500.0, 500.0)
//            scene = Scene(StackPane(Button("ble")), 300.0,300.0)
            show()
        }
    }
}

const val OSM_TILE_URL = "http://tile.openstreetmap.org/"
const val OSM_CACHE= "/.mcache/osm/"
//const val OSM_TILE_URL = "tile.openstreetmap.org/${z}/${x}/${y}.png"

fun main(args: Array<String>) {
    test(2) { age, dd ->
        println("test = " + age + dd)
    }
    Application.launch(Main::class.java, *args);
}

fun test(age: Int, myFunc: (age: Int, dd: String) -> Unit) {
    myFunc(age, "ble")
}