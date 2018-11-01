package com.project.my.kotlinpos.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.project.my.kotlinpos.Database.DB_Controller
import com.project.my.kotlinpos.MainActivity

import com.project.my.kotlinpos.R
import com.project.my.kotlinpos.RegisterActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment() {

    lateinit var btn_register:Button
    lateinit var btn_login:Button
    lateinit var name_editText:EditText
    lateinit var pass_editText:EditText

    lateinit var controller:DB_Controller

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        controller= DB_Controller(activity)
        btn_register=view.findViewById(R.id.btn_register)
        btn_login=view.findViewById(R.id.btn_login)
        name_editText=view.findViewById(R.id.name_editText)
        pass_editText=view.findViewById(R.id.pass_editText)

        btn_register.setOnClickListener(){
            var intent=Intent(activity,RegisterActivity::class.java)
            activity!!.startActivity(intent)
        }

        btn_login.setOnClickListener(){
            val data=controller.loginUser(name_editText.text.toString(),pass_editText.text.toString())
            val numRow = data.count

            if (numRow>0){

                val pref=activity!!.getSharedPreferences("Login",Context.MODE_PRIVATE)
                val editor=pref.edit()
                editor.putBoolean("session",true)
                editor.apply()

                val intent=Intent(activity,MainActivity::class.java)
                activity!!.startActivity(intent)
                Toast.makeText(activity,"Success",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }



}
