package br.com.rbueno.chucknorrisfacts.di

import br.com.rbueno.chucknorrisfacts.AppTest
import br.com.rbueno.chucknorrisfacts.BaseInstrumentedTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AppModuleTest::class,
        ActivityBuilder::class
    ]
)
interface AppComponentTest {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: AppTest): Builder

        fun build(): AppComponentTest

    }

    fun inject(app: AppTest)

    fun inject(activityTest: BaseInstrumentedTest)
}