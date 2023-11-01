package com.example.gmailapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.*
import com.github.javafaker.Faker


class MainActivity : ComponentActivity() {

    private val faker = Faker(Locale("en-GB"))
    private val timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    @SuppressLint("InflateParams", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout: LinearLayout = findViewById(R.id.scroll_linear_layout)

        for (i in 1..20) {
            val newLayout = layoutInflater.inflate(R.layout.item_email, null) as LinearLayout

            val randomName = faker.name().fullName()
            val randomContent = faker.lorem().sentence()
            val randomTime = timeFormat.format(faker.date().birthday())

            val senderTextView = newLayout.findViewById<TextView>(R.id.sender_name)
            val senderTimeTextView = newLayout.findViewById<TextView>(R.id.sender_time)
            val mailContentTextView = newLayout.findViewById<TextView>(R.id.mail_content)
            val senderCircleTextView = newLayout.findViewById<TextView>(R.id.sender_circle)

            senderTextView.text = randomName
            senderTimeTextView.text = randomTime
            mailContentTextView.text = randomContent
            mailContentTextView.maxLines = 2
            mailContentTextView.ellipsize = TextUtils.TruncateAt.END
            senderCircleTextView.text = randomName.first().toString()

            var isClicked = false
            newLayout.setOnClickListener {
                if (isClicked) {
                    senderCircleTextView.background = getDrawable(R.drawable.avatar_frame)
                    senderCircleTextView.text = randomName.first().toString()
                    senderCircleTextView.setTextColor(Color.WHITE)
                    newLayout.setBackgroundColor(Color.WHITE)
                } else {
                    newLayout.setBackgroundColor(Color.parseColor("#D3D3D3"))
                }
                isClicked = !isClicked
            }
            linearLayout.addView(newLayout)
        }
    }
}