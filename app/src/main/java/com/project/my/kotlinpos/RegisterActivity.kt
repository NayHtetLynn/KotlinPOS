package com.project.my.kotlinpos

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.project.my.kotlinpos.Database.DB_Controller
import kotlinx.android.synthetic.main.activity_register.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class RegisterActivity : AppCompatActivity() {

    lateinit var controller: DB_Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        controller= DB_Controller(this)

        btn_register.setOnClickListener(){
            var value=radioGroup.checkedRadioButtonId
            var gender=findViewById<RadioButton>(value)

            controller= DB_Controller(this)

            var createDate:String

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                createDate =  current.format(formatter)
            } else {
                var date = Date()
                val formatter = SimpleDateFormat("dd.MM.yyyy")
                createDate = formatter.format(date)
            }

            controller.createUser(edit_user_name.text.toString(),edit_password.text.toString(),edit_name.text.toString(),edit_contact.text.toString(),gender.text.toString(),createDate)

        }
    }
}
