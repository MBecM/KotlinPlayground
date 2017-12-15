package com.mbecm.kotlinmap

import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class MainView : VBox() {
    var x = 0.0;
    var y = 0.0;

    init {
        style = "-fx-background-color: red;"
        prefHeight = 200.0
//        translateX = 50.0
        setOnMousePressed {
            x = it.x - translateX
            y = it.y - translateY
        }
        setOnMouseDragged {
            translateX = it.x - x
            translateY = it.y - y
//            translateX = x - translateX
//            x = event.x
        }
    }
}