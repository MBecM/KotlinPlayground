package com.mbecm.kotlinmap

import javafx.scene.layout.StackPane

/**
 * @author Mateusz Becker
 */
open class BaseMap(mainMap: MainView) : StackPane(mainMap) {
    open val my: Int = 6
    var f: Int? = null;

    var x = 0.0
    var y = 0.0

    init {
        setOnMousePressed {
            x = it.sceneX - mainMap.translateX
            y = it.sceneY - mainMap.translateY
        }
        setOnMouseDragged {
            mainMap.translateX = it.sceneX - x
            mainMap.translateY = it.sceneY - y
//            translateX = x - translateX
//            x = event.x
        }

        setOnScroll {
            var zoom = mainMap.zoom;
            if (it.deltaY > 0) {
                zoom++;
                if (zoom > 18) {
                    zoom = 18
                }
            } else {
                zoom--;
                if (zoom < 0) {
                    zoom = 0
                }
            }
            mainMap.zoom = zoom
//            tiles.putIfAbsent(zoom, mutableMapOf())
//            loadTiles()
        }
    }
}

//class Derived : BaseMap() {
//    override var my: Int = 67
//}
