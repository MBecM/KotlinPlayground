package tornado

import javafx.application.Application
import tornadofx.App

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args);
}

class Main : App(MainView::class)