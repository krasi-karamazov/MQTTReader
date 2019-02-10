package kpk.dev.domain.usecases

import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import kpk.dev.domain.poko.UserData
import kpk.dev.model.repository.UserDataRepository

class UserDataInteractor: BaseUseCase {

    private val userDataRepository: UserDataRepository by lazy {
        UserDataRepository()
    }

    fun getUserData(): Observable<UserData>{
        return Observables.combineLatest(userDataRepository.getUserName(), userDataRepository.getUserAddress()){userName, userAddress ->
            if(userAddress.ID == userName.ID){
                UserData(userName.ID, userName.name, userAddress.address, false)
            }else{
                UserData(userName.ID, userName.name, userAddress.address, true)
            }
        }
    }

    fun cleanUp() {
        userDataRepository.cleanUp()
    }
}