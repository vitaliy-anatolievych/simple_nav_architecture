package com.study.simplecolorsarchitecture

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.core.contracts.FragmentsHolder
import com.study.core.navigator.NavigatorManager
import com.study.core.navigator.StackFragmentNavigator
import com.study.core.uiactions.AndroidUIActions
import com.study.core.utils.Animations
import com.study.core.utils.viewModelCreator
import com.study.core.viewmodels.CoreViewModel
import com.study.simplecolorsarchitecture.views.contracts.HasScreenTitle
import com.study.simplecolorsarchitecture.views.screens.currentcolor.CurrentColorFragment

/**
 * Приклад реалізації Activity
 */
class MainActivity : AppCompatActivity(), FragmentsHolder {

    private lateinit var navigator: StackFragmentNavigator

    private val activityViewModel by viewModelCreator<CoreViewModel> {
        CoreViewModel(
            uiActions = AndroidUIActions(applicationContext),
            navigatorManager = NavigatorManager(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = StackFragmentNavigator(
            activity = this,
            navigatorManager = activityViewModel.navigatorManager,
            savedInstanceState = savedInstanceState,
            containerId = R.id.fragmentContainer,
            animations = Animations(
                enterAnim = R.anim.enter,
                exitAnim = R.anim.exit,
                popEnterAnim = R.anim.pop_enter,
                popExitAnim = R.anim.pop_exit
            ),
            initialScreenCreator = { CurrentColorFragment.Screen() }
        )

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun notifyScreenUpdates() {
        navigator.notifyScreenUpdates()
        val f = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if (f is HasScreenTitle && f.getScreenTitle() != null) {
            // fragment has custom screen title -> display it
            supportActionBar?.title = f.getScreenTitle()
        } else {
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    override fun getActivityScopeViewModel(): CoreViewModel {
        return activityViewModel
    }

}