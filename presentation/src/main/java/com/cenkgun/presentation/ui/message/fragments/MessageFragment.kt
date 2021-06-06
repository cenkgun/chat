package com.cenkgun.presentation.ui.message.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cenkgun.presentation.R
import com.cenkgun.presentation.databinding.FragmentMessageBinding
import com.cenkgun.presentation.ui.message.adapter.MessageAdapter
import com.cenkgun.presentation.ui.message.viewmodels.MessageViewModel
import com.cenkgun.presentation.utils.clear
import com.cenkgun.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.dropWhile
import reactivecircus.flowbinding.android.view.clicks
import reactivecircus.flowbinding.android.view.focusChanges

@AndroidEntryPoint
class MessageFragment : Fragment() {

    private val args: MessageFragmentArgs by navArgs()

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MessageViewModel by viewModels()
    private val navController by lazy { NavHostFragment.findNavController(this) }

    private var adapter: MessageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setLoggedInUser(args.user)
        observeViewModel()
        initLayout()
    }

    private fun initLayout() {
        initToolbar()
        initMessageList()
        initClickListeners()
    }

    private fun initToolbar() {
        binding.toolbar.title = args.user.nickname
        binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
            viewModel.leaveConversation()
        }
    }

    private fun initClickListeners() {
        lifecycleScope.launchWhenStarted {
            binding.sendMessage.clicks().collect {
                val messageText = binding.messageInput.text.toString()
                viewModel.onSendButtonClicked(messageText)
            }
        }
        lifecycleScope.launchWhenStarted {
            binding.messageInput.focusChanges().collect {
                binding.messageList.smoothScrollToPosition(0)
            }
        }
    }

    private fun initMessageList() {
        adapter = MessageAdapter(args.user.id)
        val linearLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            true
        )
        linearLayoutManager.stackFromEnd = true
        binding.messageList.layoutManager = linearLayoutManager
        binding.messageList.adapter = adapter
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.messageListFlow.collect {
                adapter?.submitList(it)
                binding.messageList.smoothScrollToPosition(0)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.clearInputFlow.collect {
                binding.messageInput.clear()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.leaveConversationFlow.dropWhile { !it }.collect {
                navController.navigate(MessageFragmentDirections.actionMessageFragmentPop())
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.showToastFlow.dropWhile { it.isEmpty() }.collect { message ->
                showToast(message)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.isSendButtonEnableFlow.collect {
                binding.sendMessage.isEnabled = it
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}