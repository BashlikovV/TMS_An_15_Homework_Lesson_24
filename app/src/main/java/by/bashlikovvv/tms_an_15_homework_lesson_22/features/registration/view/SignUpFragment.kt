package by.bashlikovvv.tms_an_15_homework_lesson_22.features.registration.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentSignUpBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.App
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.registration.viewmodel.SignUpFragmentViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private val viewModel: SignUpFragmentViewModel by viewModels {
        val app = requireActivity().applicationContext as App
        SignUpFragmentViewModel.Factory(app.registrationRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignUpBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            viewModel.state.collectLatest {
                if (it.exception.isNotEmpty()) {
                    showMessage(it.exception)
                }
                if (it.success) {
                    showMessage("Success")
                }
            }
        }

        binding.registrationButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.signUp(email, password)
        }

        return binding.root
    }

    private fun showMessage(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}