package com.mbecm.kotlinmap

import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.Image
import java.awt.image.RenderedImage
import java.io.File
import java.nio.file.Files
import javax.imageio.ImageIO

/**
 * @author Mateusz Becker
 */
class TileLoader {

    val cacheDir = System.getProperty("user.home") + OSM_CACHE

    init {
        Files.createDirectories(File(cacheDir).toPath())
    }

    fun generateTile(zoom: Int, x: Long, y: Long): Image {
        val imageName = y.toString() + ".png"
        val imageDir = zoom.toString() + "/" + x + "/"

        val url = OSM_TILE_URL + imageDir + imageName

        val imageFromCache = checkCache(cacheDir + imageDir + imageName)
        val image = imageFromCache ?: Image(url, true)

        if (imageFromCache == null) {
            image.progressProperty().addListener { observable, oldValue, progress ->
                if (progress.toDouble() >= 1.0) {
                    saveToCache(image, imageDir, imageName)
                }
            }
        }
        return image
    }

    private fun checkCache(cacheFile: String): Image? {
        val file = File(cacheFile)
        if (file.exists()) {
            return Image(file.toURI().toString(), true)
        }
        return null;
    }

    private fun saveToCache(img: Image, dir: String, fileName: String) {
        val dirs = File(cacheDir, dir)
        val file = File(dirs, fileName)

        Files.createDirectories(dirs.toPath())
        val buffImage: RenderedImage? = SwingFXUtils.fromFXImage(img, null)
        if (buffImage != null) {
            ImageIO.write(buffImage, "png", file)
        }
    }
}