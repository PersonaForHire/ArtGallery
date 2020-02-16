package ie.wit.artgallery.activities


import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity


import com.google.firebase.auth.FirebaseAuth
import ie.wit.artgallery.R
import ie.wit.artgallery.helpers.readImage
import ie.wit.artgallery.helpers.readImageFromPath
import ie.wit.artgallery.helpers.showImagePicker
import ie.wit.artgallery.main.MainApp
import ie.wit.artgallery.models.ArtModel
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_post.sign_out_button
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast


class PostActivity : AppCompatActivity(), AnkoLogger {
    var art = ArtModel()
    var edit = false
    val IMAGE_REQUEST = 1

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        app = application as MainApp

        sign_out_button.setOnClickListener {
            startActivity(LogInActivity.getLaunchIntent(this))
            FirebaseAuth.getInstance().signOut()
        }
        if (intent.hasExtra("post_edit")) {
            edit = true
            art = intent.extras?.getParcelable<ArtModel>("post_edit")!!
            post_button.setText("Post")
            postImage.setImageBitmap(readImageFromPath(this, art.image))
            if (art.image != null) {
                chooseImage.setText(R.string.change_post_image)
            }

        }
        post_button.setOnClickListener() {


                if (edit) {
                    app.arts.update(art.copy())
                } else {
                    app.arts.create(art.copy())
                }

            setResult(AppCompatActivity.RESULT_OK)
            finish()

        }
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_post, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete->{
                app.arts.delete(art)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    art.image = data.getData().toString()
                    postImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_post_image)
                }
            }
        }
    }



    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, PostActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
