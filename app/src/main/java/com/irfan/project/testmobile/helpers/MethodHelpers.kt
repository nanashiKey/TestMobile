package com.irfan.project.testmobile.helpers

import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


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

    fun setWindowsBar(saved : AppCompatActivity, color: Int){
        val windows = saved.window
        windows.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        windows.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        windows.setStatusBarColor(ContextCompat.getColor(ctx, color))
    }

    fun setNavBackgroundLuna(saved: AppCompatActivity, color : Int){
        val windows = saved.window
        windows.navigationBarColor = saved.resources.getColor(color)
    }

}