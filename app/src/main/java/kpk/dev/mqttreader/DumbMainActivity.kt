package kpk.dev.mqttreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kpk.dev.mqttreader.extensions.attachFragment
import kpk.dev.presentation.view.UserDataListFragment

class DumbMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attachFragment(R.id.fl_container, UserDataListFragment.getInstance(null), UserDataListFragment.TAG)
    }
}
