package com.cenkgun.presentation.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.cenkgun.presentation.databinding.FragmentLoginBinding
import com.cenkgun.presentation.ui.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import reactivecircus.flowbinding.android.view.clicks
import reactivecircus.flowbinding.android.widget.textChanges

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()
    private val navController by lazy { findNavController(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        observeViewModel()
    }

    private fun initListeners() {
        lifecycleScope.launchWhenStarted {
            binding.nicknameInput.textChanges().collect {
                viewModel.checkNickname(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            binding.enterButton.clicks().collect {
                viewModel.login()
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.navigateToMessagesFlow.collect { user ->
                user?.let {
                    navController.navigate(
                        LoginFragmentDirections.actionLoginFragmentToMessageFragment(user)
                    )
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.continueButtonIsEnableFlow.collect { isEnabled ->
                binding.enterButton.isEnabled = isEnabled
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}