package ie.wit.artgallery.main

import android.app.Application
import ie.wit.artgallery.models.ArtModel
import ie.wit.artgallery.store.PostMemStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

   val arts = PostMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Gallery started")
    }
}