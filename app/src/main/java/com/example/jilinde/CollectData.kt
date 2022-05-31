package com.example.jilinde

import android.R.attr.password
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_collect_data.*


class CollectData : AppCompatActivity() {
    var userName:String=""
    var conPassword=""
    var userEmail:String=""
    var pass:String=""
    var confirmPassword:String=""
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect_data)


       auth= FirebaseAuth.getInstance()
        fun onLogin(view: View) {
            LoginScreen().show(supportFragmentManager, "MyCustomFragment")
        }
      toLogin.setOnClickListener{
             val intent = Intent(applicationContext,LoginScreen::class.java)
          startActivity(intent)
      }

        btnSubmit.setOnClickListener{
            captureInput()
        }









    }

    private fun captureInput() {
            userName=PersonName.text.toString()
          userEmail=PersonEmail.text.toString()
        conPassword=ConfirmPassword.text.toString()
        pass=PersonPassword.text.toString()

        if (pass.equals(conPassword)){

            confirmPassword=pass
        }else{
            Toast.makeText(applicationContext,"Passwords Do Not Match",Toast.LENGTH_LONG).show()
        }
        registerToFirebase(userEmail,confirmPassword)





    }


    public override fun onStart() {
        super.onStart()
        val currentUser= auth.currentUser
        if (currentUser !=null){
            updateUi();
        }
    }
    private fun updateUi(){
        val intent=Intent(applicationContext,Homepage::class.java)
        startActivity(intent)
    }
    private fun registerToFirebase(userEmail: String, confirmPassword: String) {
        auth.signInWithEmailAndPassword(userEmail,confirmPassword)
                .addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(applicationContext,"Account Created Successfully",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(applicationContext,"Unable to create Account,try again",Toast.LENGTH_LONG).show()
                        Log.d("auth","details are :" + it.exception)
                    }
                }

    }


}