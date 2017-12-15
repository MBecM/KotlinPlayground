package com.mbecm.kotlinmap

/**
 * @author Mateusz Becker
 */
open class Base {
    open val my: Int = 6
    var f: Int? = null;
}

class Derived : Base() {
    override var my: Int = 67
}
