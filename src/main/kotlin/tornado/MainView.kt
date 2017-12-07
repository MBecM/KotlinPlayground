package tornado

import javafx.scene.Node
import javafx.scene.Parent
import tornadofx.*

class MainView : View() {
    override val root = hbox {
            label("my label")
        }
}