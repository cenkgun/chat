package com.cenkgun.presentation.ui.login.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cenkgun.domain.helper.LoginValidationHelper
import com.cenkgun.domain.usecases.GetLoggedInUserUseCase
import com.cenkgun.domain.usecases.GetUserByNicknameUseCase
import com.cenkgun.domain.usecases.SaveLoggedInUserUseCase
import com.cenkgun.domain.usecases.SaveUserListUseCase
import com.cenkgun.presentation.mapper.SessionModelMapper
import com.cenkgun.presentation.mapper.UserModelMapper
import com.cenkgun.presentation.models.SessionModel
import com.cenkgun.presentation.models.UserModel
import com.cenkgun.presentation.utils.stateFlow
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
    private val userModelMapper: UserModelMapper,
    private val sessionModelMapper: SessionModelMapper,
    private val loginValidationHelper: LoginValidationHelper,
    private val state: SavedStateHandle
) : ViewModel() {

    private val _nicknameFlow
        get() : StateFlow<String?> = state.stateFlow(
            viewModelScope, NICKNAME_STATE_KEY, null
        )

    private val _navigateToMessagesFlow = MutableStateFlow<UserModel?>(null)
    val navigateToMessagesFlow get() : StateFlow<UserModel?> = _navigateToMessagesFlow

    private val _continueButtonIsEnableFlow = MutableStateFlow(false)
    val continueButtonIsEnableFlow get() = _continueButtonIsEnableFlow

    init {
        checkLoggedInUser()
    }

    fun login() {
        viewModelScope.launch {
            _nicknameFlow.value?.let { nickname ->
                val existUser = getUserByNicknameUseCase(nickname)
                existUser?.let { user ->
                    userModelMapper.mapToDomain(user).apply {
                        saveLoggedUser(this)
                        _navigateToMessagesFlow.value = this
                    }
                    return@launch
                }
                generateUserModelByNickname(nickname).apply {
                    saveUser(this)
                    saveLoggedUser(this)
                    _navigateToMessagesFlow.value = this
                }
            }
        }
    }

    private fun generateUserModelByNickname(nickname: String) = UserModel(
        UUID.randomUUID().toString(), nickname, null
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

    fun checkNickname(nickname: CharSequence) {
        val sterilizedNickname = nickname.toString().trim()
        if (loginValidationHelper.isValidNickname(sterilizedNickname)) {
            state[NICKNAME_STATE_KEY] = sterilizedNickname
            _continueButtonIsEnableFlow.value = true
            return
        }
        _continueButtonIsEnableFlow.value = false
    }

    companion object {
        private const val NICKNAME_STATE_KEY = "nicknameStateKey"
    }
}