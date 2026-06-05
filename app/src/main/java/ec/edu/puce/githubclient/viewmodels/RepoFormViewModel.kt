package ec.edu.puce.githubclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ec.edu.puce.githubclient.Models.RepositoryPayload
import ec.edu.puce.githubclient.services.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoFormViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    private val _errorMsg = MutableStateFlow<String?>(null)
    val errMsg: StateFlow<String?> = _errorMsg.asStateFlow()

    fun createRepository(
        name: String,
        description: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _isSuccess.value = false
            _errorMsg.value = null

            try {
                val repo = RepositoryPayload(
                    name = name,
                    description = description
                )

                RetrofitClient.apiService.createRepository(repo)
                _isSuccess.value = true

            } catch (e: Exception) {
                _errorMsg.value = "Error al crear repositorio: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateRepository(
        owner: String,
        oldRepoName: String,
        newName: String,
        description: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _isSuccess.value = false
            _errorMsg.value = null

            try {
                val repo = RepositoryPayload(
                    name = newName,
                    description = description
                )

                RetrofitClient.apiService.updateRepository(
                    owner = owner,
                    repo = oldRepoName,
                    repository = repo
                )

                _isSuccess.value = true

            } catch (e: Exception) {
                _errorMsg.value = "Error al actualizar repositorio: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resetSuccess() {
        _isSuccess.value = false
    }

    fun resetError() {
        _errorMsg.value = null
    }
}