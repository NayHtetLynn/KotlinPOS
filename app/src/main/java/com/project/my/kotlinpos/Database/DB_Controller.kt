package com.project.my.kotlinpos.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v4.app.FragmentActivity
import android.widget.Toast

class DB_Controller : SQLiteOpenHelper{

    var context: Context? = null

    constructor(context: FragmentActivity?) : super(context,"KotlinPOS.db",null,1){
        this.context = context
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table USER(UserId Integer Primary Key Autoincrement, UserName Text, Password Text, Name Text, ContactNo Text, Gender Text, CreateDate Text);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS USER;")
    }

    fun createUser(userName:String,password:String,name:String,contactNo:String, gender:String,createDate:String){
        val db : SQLiteDatabase = this.writableDatabase

        val query="SELECT * FROM USER WHERE UserName='" + userName + "'"
        val result : Cursor = db.rawQuery(query,null)

        val contentValues= ContentValues()
        contentValues.put("UserName",userName)
        contentValues.put("Password",password)
        contentValues.put("Name",name)
        contentValues.put("ContactNo",contactNo)
        contentValues.put("Gender",gender)
        contentValues.put("CreateDate",createDate)

        if (result.count==0){
            db.insert("USER",null,contentValues)
            Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Already Exist!", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun loginUser(userName: String,password: String) : Cursor {
        val database = this.writableDatabase
        return database.rawQuery("SELECT * FROM USER WHERE UserName='$userName'AND Password='$password'", null)
    }

}