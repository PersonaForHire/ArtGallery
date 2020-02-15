package ie.wit.artgallery.main

import android.app.Application
import ie.wit.artgallery.models.ArtModel
import ie.wit.artgallery.store.PostJSONStore
import ie.wit.artgallery.store.PostMemStore
import ie.wit.artgallery.store.PostStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

   lateinit var arts : PostStore

    override fun onCreate() {
        super.onCreate()
        arts = PostJSONStore(applicationContext)
        info("Gallery started")
    }
}