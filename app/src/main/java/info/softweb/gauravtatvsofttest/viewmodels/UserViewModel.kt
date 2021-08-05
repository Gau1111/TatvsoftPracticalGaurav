package info.softweb.gauravtatvsofttest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.softweb.gauravtatvsofttest.model.UserListResponseModel
import info.softweb.gauravtatvsofttest.repositories.UserRepository
import kotlinx.coroutines.*

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val userList = MutableLiveData<UserListResponseModel>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllUsers(page:Int) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = userRepository.getAllUsers(page)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    userList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}