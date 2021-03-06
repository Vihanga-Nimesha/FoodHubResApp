package com.food.foodhub

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var actionBar = supportActionBar
        actionBar?.hide()


        //Signup
        val loginClick = findViewById<TextView>(R.id.LogIn)
        //login
        val signUpClick = findViewById<TextView>(R.id.SignUp)
        //button
        val btn = findViewById<Button>(R.id.LogInBtn)

        val logInLayout = findViewById<View>(R.id.logInlayout) as LinearLayout
        val signUpLayout = findViewById<View>(R.id.signUpLayout) as LinearLayout

        loginClick.setOnClickListener {

            signUpLayout.visibility = View.GONE
            logInLayout.visibility = View.VISIBLE
            btn.setText("Login")

            signUpClick.setBackgroundColor(resources.getColor(R.color.white))
            signUpClick.setTextColor(resources.getColor(R.color.black))

            loginClick.setBackgroundColor(resources.getColor(R.color.pinColor))
            loginClick.setTextColor(resources.getColor(R.color.white))

        }

        signUpClick.setOnClickListener {

            logInLayout.visibility = View.GONE
            signUpLayout.visibility = View.VISIBLE
            btn.setText("Sign Up")


            loginClick.setBackgroundColor(resources.getColor(R.color.white))
            loginClick.setTextColor(resources.getColor(R.color.black))

            signUpClick.setBackgroundColor(resources.getColor(R.color.pinColor))
            signUpClick.setTextColor(resources.getColor(R.color.white))
        }
        val loginButtonClick = findViewById<Button>(R.id.LogInBtn)
        loginButtonClick.setOnClickListener {
            LoginValidation()
        }


        val registerBtn = findViewById<Button>(R.id.SignUpBtn)
        registerBtn.setOnClickListener {
            RegisterCustomer()
        }

    }

    private fun RegisterCustomer() {
        auth = FirebaseAuth.getInstance()

        val emailET = findViewById<EditText>(R.id.SignUpEmail)
        val pwET = findViewById<EditText>(R.id.SignUpPw)
        var email: String = emailET.text.toString()
        var password: String = pwET.text.toString()


        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val firestoreDB = FirebaseFirestore.getInstance()

                val fullNameET = findViewById<EditText>(R.id.fName)
                val addressET = findViewById<EditText>(R.id.address)
                val phoneNoET = findViewById<EditText>(R.id.PhoNo)

                var fullName: String = fullNameET.text.toString()
                var address: String = addressET.text.toString()
                var phoneNo: String = phoneNoET.text.toString()


                val RegistrationData: MutableMap<String, Any> = HashMap()
                RegistrationData["Full Name"] = fullName
                RegistrationData["Address"] = address
                RegistrationData["Phone Number"] = phoneNo
                RegistrationData["Email"] = email

                auth.currentUser?.let {
                    firestoreDB.collection("Users")
                        .document(it.uid)
                        .set(RegistrationData)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this@MainActivity,
                                "record added successfully ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                this@MainActivity,
                                "record Failed to add ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                // val intent= Intent(this,MainActivity::class.java)
                // startActivity(intent)
                // finish()


            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }

    }

    //private fun RegisterUser() {
    //    val fullNameET = findViewById<EditText>(R.id.fName)
    //    val addressET = findViewById<EditText>(R.id.address)
    //    val  phoneNoET = findViewById<EditText>(R.id.PhoNo)
    //    val emailET = findViewById<EditText>(R.id.eMail)

    //    var fullName:String = fullNameET.text.toString()
    //    var address:String = addressET.text.toString()
    //    var phoneNo:String = phoneNoET.text.toString()
    //    var email:String = emailET.text.toString()
    //  //  val currentFirebaseUser = FirebaseAuth.getInstance().currentUser

    //    database = FirebaseDatabase.getInstance().getReference("Users")

    //    val User = com.food.foodhub.User(
    //        email,
    //        address,
    //        phoneNo)

    //    database.child(fullName).setValue(User).addOnSuccessListener {

    //    }


    private fun LoginValidation() {

        auth = FirebaseAuth.getInstance()

        val passwordET = findViewById<EditText>(R.id.password)
        val emailET = findViewById<EditText>(R.id.eMail)

        var email: String = emailET.text.toString()
        var password: String = passwordET.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                }
            })
        //startActivity(Intent(this,Home::class.java))
        //finish()
    }
}





