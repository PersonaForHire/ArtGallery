package ie.wit.artgallery.activities


import android.content.Context
import android.content.Intent

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth

import ie.wit.artgallery.R
import ie.wit.artgallery.models.ArtModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.sign_out_button



class HomeActivity : AppCompatActivity() {
    var art=ArtModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupUI()
        post_button.setOnClickListener {
            art = editTxtComment.text.toString()
        }
    }

    private fun setupUI() {
        sign_out_button.setOnClickListener {
            signOut()
        }
    }
    private fun signOut() {
        startActivity(LogInActivity.getLaunchIntent(this))
        FirebaseAuth.getInstance().signOut()
    }
    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, HomeActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
