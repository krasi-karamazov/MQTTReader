package kpk.dev.presentation

import kpk.dev.domain.poko.UserData

sealed class UserDataListState {
    abstract val data: List<UserData>
}

data class DefaultState(override val data: List<UserData>): UserDataListState()
data class AdditionState(val newData: UserData?, override val data: List<UserData>): UserDataListState()
data class ErrorState(val errorMessage: String, override val data: List<UserData>): UserDataListState()