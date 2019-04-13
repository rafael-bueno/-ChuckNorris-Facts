package br.com.rbueno.chucknorrisfacts.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import br.com.rbueno.chucknorrisfacts.AppTest

class AppRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, AppTest::class.java.name, context)
    }

}