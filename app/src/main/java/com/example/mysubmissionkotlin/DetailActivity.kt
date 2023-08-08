package com.example.mysubmissionkotlin

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_THING = "extra_thing"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgItemPhotoDetail: ImageView = findViewById(R.id.img_item_photo_detail)
        val tvItemNameDetail: TextView = findViewById(R.id.tv_item_name_detail)
        val tvItemDescriptionDetail: TextView = findViewById(R.id.tv_item_description_detail)
        val tvItemCompanyName: TextView = findViewById(R.id.tv_item_company_name)
        val tvItemConsoleType: TextView = findViewById(R.id.tv_item_console_type)

        val btnShare: Button = findViewById(R.id.btn_share)

        val thing = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_THING, Thing::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_THING)
        }

        if (thing != null) {
            val img = thing.photo.toInt()
            val text1 = thing.name.toString()
            val text2 = thing.description.toString()
            val text3 = thing.company.toString()
            val text4 = thing.consoleType.toString()

            tvItemNameDetail.text = text1
            tvItemDescriptionDetail.text = text2
            tvItemCompanyName.text = text3
            tvItemConsoleType.text = text4
            imgItemPhotoDetail.setImageResource(img)

            btnShare.setOnClickListener(View.OnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, text1)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            })
        }

    }
}