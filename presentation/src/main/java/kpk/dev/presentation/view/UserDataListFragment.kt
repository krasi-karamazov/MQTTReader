package kpk.dev.presentation.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_user_data_list.*
import kpk.dev.presentation.*
import kpk.dev.presentation.adapter.UserCardsAdapter
import kpk.dev.presentation.viewmodel.UsersListViewModel

class UserDataListFragment: BaseFragment(){

    companion object {
        val TAG = UserDataListFragment::class.java.simpleName
        fun getInstance(args: Bundle?): UserDataListFragment {
            val userDataListFragment = UserDataListFragment()
            userDataListFragment.arguments = args
            return userDataListFragment
        }
    }

    val viewModel:UsersListViewModel by lazy {
        ViewModelProviders.of(this).get(UsersListViewModel::class.java)
    }

    val userCardsAdapter: UserCardsAdapter by lazy {
        UserCardsAdapter(this.activity!!)
    }

    override fun getLayoutId(): Int = R.layout.fragment_user_data_list

    override fun init() {
        rvUserCards.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = userCardsAdapter
        }
        viewModel.getUserData().observe(this, Observer<UserDataListState> {
            when(it){
                is DefaultState -> {
                    if(userCardsAdapter.itemCount == 0) {
                        userCardsAdapter.addAllData(it.data)
                    }
                }
                is ErrorState -> Toast.makeText(activity, it.errorMessage, Toast.LENGTH_SHORT).show()
                is AdditionState -> {
                    userCardsAdapter.addData(it.newData)
                }
            }
        })
    }

    override fun onStop() {
        viewModel.cleanUpAfterShutDown()
        super.onStop()
    }
}