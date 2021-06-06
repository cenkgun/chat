package com.cenkgun.presentation.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cenkgun.domain.providers.ResourceProvider
import com.cenkgun.domain.usecases.GetLoggedInUserUseCase
import com.cenkgun.domain.usecases.GetUserByNicknameUseCase
import com.cenkgun.domain.usecases.SaveLoggedInUserUseCase
import com.cenkgun.domain.usecases.SaveUserListUseCase
import com.cenkgun.presentation.R
import com.cenkgun.presentation.mapper.SessionModelMapper
import com.cenkgun.presentation.mapper.UserModelMapper
import com.cenkgun.presentation.models.SessionModel
import com.cenkgun.presentation.models.UserModel
import com.cenkgun.presentation.ui.login.validation.LoginValidationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveUserListUseCase: SaveUserListUseCase,
    private val saveLoggedInUserUseCase: SaveLoggedInUserUseCase,
    private val loggedInUserUseCase: GetLoggedInUserUseCase,
    private val getUserByNicknameUseCase: GetUserByNicknameUseCase,
    private val resourceProvider: ResourceProvider,
    private val userModelMapper: UserModelMapper,
    private val sessionModelMapper: SessionModelMapper
) : ViewModel() {

    private val _navigateToMessagesFlow = MutableStateFlow<UserModel?>(null)
    val navigateToMessagesFlow get() : StateFlow<UserModel?> = _navigateToMessagesFlow

    private val _showErrorFlow = MutableStateFlow("")
    val showErrorFlow get() : StateFlow<String> = _showErrorFlow

    init {
        checkLoggedInUser()
    }

    fun login(nickname: String) {
        viewModelScope.launch {
            if (LoginValidationHelper.isValidNickname(nickname)) {
                val existUser = getUserByNicknameUseCase(nickname)
                existUser?.let {
                    userModelMapper.mapToDomain(it).apply {
                        saveLoggedUser(this)
                        _navigateToMessagesFlow.value = this
                    }
                    return@launch
                }
                getUser(nickname).apply {
                    saveUser(this)
                    saveLoggedUser(this)
                    _navigateToMessagesFlow.value = this
                }
            } else {
                _showErrorFlow.value =
                    resourceProvider.getString(R.string.login_validation_error_message)
            }
        }
    }

    private fun getUser(nickname: String) = UserModel(
        id = UUID.randomUUID().toString(),
        nickname = nickname,
        avatarURL = null
    )

    private suspend fun saveLoggedUser(user: UserModel) {
        val session = SessionModel(user)
        saveLoggedInUserUseCase(sessionModelMapper.mapToModel(session))
    }

    private suspend fun saveUser(user: UserModel) {
        saveUserListUseCase(listOf(userModelMapper.mapToModel(user)))
    }

    private fun checkLoggedInUser() {
        viewModelScope.launch {
            loggedInUserUseCase().collect {
                _navigateToMessagesFlow.value = sessionModelMapper.mapToDomain(it).user
            }
        }
    }
}