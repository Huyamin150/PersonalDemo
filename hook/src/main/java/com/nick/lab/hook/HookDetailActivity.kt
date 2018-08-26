package com.nick.lab.hook

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

/**
 * Created by Nick.Hu on 2018/8/26.
 * Mail:huyamin@yonghui.cn | huyamin@hotmail.com
 * Tel: +8618521550285
 */
class HookDetailActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val text = TextView(this)
        text.text = "HookDetailActivity"
        setContentView(text)
    }
}