package com.cenkgun.presentation.ui.message.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cenkgun.domain.models.Message
import com.cenkgun.domain.providers.ResourceProvider
import com.cenkgun.domain.usecases.*
import com.cenkgun.presentation.R
import com.cenkgun.presentation.mapper.MessageModelMapper
import com.cenkgun.presentation.mapper.UserModelMapper
import com.cenkgun.presentation.models.MessageModel
import com.cenkgun.presentation.models.UserModel
import com.cenkgun.presentation.utils.stateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val saveMessageListUseCase: SaveMessageListUseCase,
    private val getMessageListFromRemoteUseCase: GetMessageListFromRemoteUseCase,
    private val getMessageListFromDatabaseUseCase: GetMessageListFromDatabaseUseCase,
    private val getFlowMessageListFromDatabaseUseCase: GetFlowMessageListFromDatabaseUseCase,
    private val deleteLoggedInUserUseCase: DeleteLoggedInUserUseCase,
    private val saveUserListUseCase: SaveUserListUseCase,
    private val resourceProvider: ResourceProvider,
    private val userMapper: UserModelMapper,
    private val messageMapper: MessageModelMapper,
    private val state: SavedStateHandle
) : ViewModel() {

    init {
        checkExistMessageList()
        observeMessageListFromDatabase()
    }

    val messageListFlow
        get() : StateFlow<List<MessageModel>> = state.stateFlow(
            viewModelScope, MESSAGE_LIST_SAVED_STATE_KEY, listOf()
        )

    private val _clearInputFlow = MutableStateFlow(Any())
    val clearInputFlow get() : StateFlow<Any> = _clearInputFlow

    private val _leaveConversationFlow = MutableStateFlow(false)
    val leaveConversationFlow get(): StateFlow<Boolean> = _leaveConversationFlow

    private val _showToastFlow = MutableStateFlow("")
    val showToastFlow get() : StateFlow<String> = _showToastFlow

    private val _isSendButtonEnableFlow = MutableStateFlow(true)
    val isSendButtonEnableFlow get() = _isSendButtonEnableFlow

    private fun saveMessage(messageText: String) {
        getLoggedInUser()?.let { user ->
            val message = getMappedMessage(messageText, user)
            viewModelScope.launch {
                saveMessageListUseCase(listOf(message))
            }
        }
    }

    private fun getMessageListFromRemote() {
        viewModelScope.launch {
            getMessageListFromRemoteUseCase()
                .catch {
                    deleteLoggedInUserUseCase()
                    _isSendButtonEnableFlow.value = false
                    _showToastFlow.value =
                        resourceProvider.getString(R.string.check_your_connection)
                }.map {
                    messageMapper.mapToModelList(it)
                }.collect { messageList ->
                    val userList = messageList.map { messageModel -> messageModel.user }
                    saveUserListUseCase(userMapper.mapToModelList(userList))
                    saveMessageListUseCase(messageMapper.mapToDomainList(messageList))
                    state[MESSAGE_LIST_SAVED_STATE_KEY] = messageList
                }
        }
    }

    private fun observeMessageListFromDatabase() {
        viewModelScope.launch {
            getFlowMessageListFromDatabaseUseCase().map {
                messageMapper.mapToModelList(it)
            }.collect {
                state[MESSAGE_LIST_SAVED_STATE_KEY] = it
            }
        }
    }

    private fun checkExistMessageList() {
        viewModelScope.launch {
            val messageList = getMessageListFromDatabaseUseCase()
            messageList.ifEmpty {
                getMessageListFromRemote()
            }
        }
    }

    private fun getMappedMessage(
        messageText: String,
        user: UserModel
    ): Message {
        val message = MessageModel(
            messageText,
            DateTime.now().millis,
            user
        )
        return messageMapper.mapToDomain(message)
    }

    private fun getLoggedInUser(): UserModel? {
        return state.get<UserModel>(USER_SAVED_STATE_KEY)
    }

    fun setLoggedInUser(user: UserModel) {
        state[USER_SAVED_STATE_KEY] = user
    }

    fun leaveConversation() {
        viewModelScope.launch {
            deleteLoggedInUserUseCase()
            _leaveConversationFlow.value = true
        }
    }

    fun handleOnSendButtonClicked(messageText: String) {
        saveMessage(messageText)
        _clearInputFlow.value = Any()
    }

    fun handleOnBackPressed() {
        leaveConversation()
    }

    companion object {
        private const val MESSAGE_LIST_SAVED_STATE_KEY = "messageListSavedStateKey"
        private const val USER_SAVED_STATE_KEY = "userSavedStateKey"
    }
}