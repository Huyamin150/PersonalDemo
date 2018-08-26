package com.nick.lab.hook.old

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

/**
 * Created by Nick.Hu on 2018/8/26.
 * Mail:huyamin@yonghui.cn | huyamin@hotmail.com
 * Tel: +8618521550285
 */
class TestActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val test = TextView(this)
        test.setText("TestActivity")
        setContentView(test)
    }
}