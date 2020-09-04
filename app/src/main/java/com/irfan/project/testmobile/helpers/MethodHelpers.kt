package com.irfan.project.testmobile.helpers

import android.content.Context
import android.content.Intent
import android.widget.Toast


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 *   here you can add any method that will help you
 *   to execute data
 **/
class MethodHelpers {
    lateinit var ctx : Context
    constructor(){}
    constructor(ctx : Context){
        this.ctx = ctx
    }
    fun showShortToast(msg : String){
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
    }
    fun showLongToast(msg : String){
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show()
    }

    infix fun <T : Any> Context.pindahKe(classTujuan : Class<T>){
        startActivity(Intent(this, classTujuan))
    }

}