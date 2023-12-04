package by.bashlikovvv.tms_an_15_homework_lesson_22.features.registration.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentSignInBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.Destination
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.navigateToDestination
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.registration.viewmodel.SignInFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val viewModel: SignInFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignInBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            viewModel.state.collectLatest {
                if (it.exception.isNotEmpty()) {
                    showMessage(it.exception)
                }
                if (it.success) {
                    showMessage("Success")
                    navigateToDestination(Destination.Notes)
                }
            }
        }

        binding.registrationButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.signIn(email, password)
        }

        return binding.root
    }

    private fun showMessage(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

}