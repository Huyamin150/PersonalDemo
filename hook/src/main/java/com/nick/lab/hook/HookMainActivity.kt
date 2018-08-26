package com.nick.lab.hook

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hook_main.*
import java.lang.reflect.Proxy
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.staticProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

class HookMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hook_main)
        button.setOnClickListener {
            startActivity(Intent(this,HookDetailActivity::class.java))
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        hookActivityManager()
    }

    @SuppressLint("PrivateApi")
    fun hookActivityManager() {
        var singleon: Any? = null
        var iactivityManager: Any? = null
        val cls = Class.forName("android.app.ActivityManager")
        cls.kotlin.staticProperties.forEach {
            if (it.name == "IActivityManagerSingleton") {
                it.isAccessible = true
                singleon = it.get()
            }
        }
        val signleonCls = Class.forName( "android.util.Singleton").kotlin as KClass<Any>
        var fieldActivityManager : KProperty<*>? = null
        signleonCls.memberProperties.forEach {
            if (it.name == "mInstance") {
                it.isAccessible = true
                fieldActivityManager = it
                iactivityManager = it.get(singleon!!)
            }
        }

        val iActivityManagerInterface = Class.forName("android.app.IActivityManager")
        val proxy = Proxy.newProxyInstance(Thread.currentThread().contextClassLoader,
                arrayOf(iActivityManagerInterface), ActivityManagerHandler(iactivityManager!!))
        fieldActivityManager?.javaField?.set(singleon,proxy)

    }

}
