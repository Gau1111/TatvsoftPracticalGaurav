package info.softweb.gauravtatvsofttest.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.softweb.gauravtatvsofttest.repositories.UserRepository
import info.softweb.gauravtatvsofttest.viewmodels.UserViewModel

class UserViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            UserViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}