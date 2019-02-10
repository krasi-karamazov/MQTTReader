package kpk.dev.mqttreader.extensions

import androidx.appcompat.app.AppCompatActivity
import kpk.dev.presentation.view.BaseFragment

fun AppCompatActivity.attachFragment(containerId: Int, baseFragment: BaseFragment, tag: String) {
    supportFragmentManager.beginTransaction()
        .add(containerId, baseFragment, tag)
        .commit()
}