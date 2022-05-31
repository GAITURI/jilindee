package com.example.jilinde

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_collect_data.*
import kotlinx.android.synthetic.main.activity_login_screen.*


class LoginScreen: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        return inflater.inflate(R.layout.activity_login_screen, container, false)
        val appContext = requireContext().applicationContext


        forgotPass.setOnClickListener{
            val intent = Intent(context,LoginScreen::class.java)
            startActivity(intent)
        }




    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}
