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
import com.cenkgun.presentation.ui.login.validation.LoginValidationHelper
import com.cenkgun.presentation.ui.login.viewmodel.LoginViewModel
import com.cenkgun.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.dropWhile
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
        lifecycleScope.launchWhenCreated {
            binding.nicknameInput.textChanges().collect {
                binding.enterButton.isEnabled =
                    LoginValidationHelper.buttonCanBeEnable(it.toString())
            }
        }
        lifecycleScope.launchWhenCreated {
            binding.enterButton.clicks().collect {
                val nickname = binding.nicknameInput.text.toString()
                viewModel.login(nickname)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.navigateToMessagesFlow.collect { user ->
                user?.let {
                    navController.navigate(
                        LoginFragmentDirections.actionLoginFragmentToMessageFragment(user)
                    )
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.showErrorFlow.dropWhile { it == 0 }.collect { stringRes ->
                showToast(getString(stringRes))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}