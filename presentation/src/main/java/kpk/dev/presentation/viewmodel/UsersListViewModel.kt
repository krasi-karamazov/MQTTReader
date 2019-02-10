package kpk.dev.presentation.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kpk.dev.domain.poko.UserData
import kpk.dev.domain.usecases.UserDataInteractor
import kpk.dev.presentation.AdditionState
import kpk.dev.presentation.DefaultState
import kpk.dev.presentation.ErrorState
import kpk.dev.presentation.UserDataListState

class UsersListViewModel: ViewModel() {
    private val stateLiveData = MutableLiveData<UserDataListState>()
    private val userDataUseCase = UserDataInteractor()
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    init {
        stateLiveData.value = DefaultState(emptyList())
    }

    fun getUserData(): LiveData<UserDataListState>{
        compositeDisposable.add(userDataUseCase.getUserData().
            subscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread()).
            subscribe(this::onNewDataReceived, this::onErrorReceived))
        return stateLiveData
    }

    private fun onNewDataReceived(userData: UserData) {
        val currentUserDataList = getCurrentData().toMutableList()
        if (!userData.ignore) {
            currentUserDataList.add(userData)
            stateLiveData.value = AdditionState(userData, getCurrentData())
            handler.postDelayed( { stateLiveData.value = DefaultState(getCurrentData()) }, 2000)
        }
    }

    fun cleanUpAfterShutDown() {
        compositeDisposable.dispose()
        userDataUseCase.cleanUp()
    }

    private fun onErrorReceived(throwable: Throwable) {
        stateLiveData.value = ErrorState("An Error Occurred", getCurrentData())
    }

    private fun getCurrentData() = stateLiveData.value?.data ?: emptyList()
}