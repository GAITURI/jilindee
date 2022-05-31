package com.example.jilinde

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView

class Products : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_products, container, false)

        val appContext = requireContext().applicationContext

        val scrollView = ScrollView(appContext)
        val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        scrollView.layoutParams = layoutParams







        val linearLayout = LinearLayout(context)
        val linearParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = linearParams

        scrollView.addView(linearLayout)

        val imageView1 = ImageView(appContext)
        val params1 =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
        //setting margin
        params1.setMargins(0, 0, 0, 0)
        //aligning the layout to center of the screen
        params1.gravity = Gravity.CENTER
        imageView1.layoutParams = params1
        //setting our own downloaded/custom image to the imageView
        imageView1.setImageResource(R.drawable.image1)
        linearLayout.addView(imageView1)
         val imageView2 = ImageView(appContext)
        val params2 =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
        params2.setMargins(0, 0, 0, 0)
        params2.gravity = Gravity.CENTER
        imageView2.layoutParams = params2
        imageView2.setImageResource(R.drawable.image2)
        linearLayout.addView(imageView2)

        val imageView3 = ImageView(appContext)
        val params3 =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
        params3.setMargins(0, 0, 0, 0)
        params3.gravity = Gravity.CENTER
        imageView3.layoutParams = params3
        imageView3.setImageResource(R.drawable.image3)
        linearLayout.addView(imageView3)

        val imageView4 = ImageView(appContext)
        val params4 =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
        params4.setMargins(0, 0, 0, 0)
        params4.gravity = Gravity.CENTER
        imageView4.layoutParams = params4
        imageView4.setImageResource(R.drawable.image4)
        linearLayout.addView(imageView4)

        val linearLayout1 =view.findViewById<LinearLayout>(R.id.layout)
        linearLayout1?.addView(scrollView  )
         return view




    }
    }











