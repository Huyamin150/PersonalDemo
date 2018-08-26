package com.nick.lab.hook

import android.content.ComponentName
import android.content.Intent
import com.nick.lab.hook.old.TestActivity
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * Created by Nick.Hu on 2018/8/26.
 * Mail:huyamin@yonghui.cn | huyamin@hotmail.com
 * Tel: +8618521550285
 */
class ActivityManagerHandler(val mBase:Any) :InvocationHandler {

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>): Any? {
        if (method?.name == "startActivity") {
                args.forEach {
                    if (it is Intent){
                        it.component =  ComponentName("com.nick.lab.hook", TestActivity::class.java.name)
                    }
                }
            }

        return method?.invoke(mBase, *args)
    }
}