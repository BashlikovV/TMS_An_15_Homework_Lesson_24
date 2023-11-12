package by.bashlikovvv.tms_an_15_homework_lesson_22.features.registration.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import by.bashlikovvv.tms_an_15_homework_lesson_22.R
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.also {
            setUpSignUpButton(it)
            setUpSignInButton(it)
        }

        return binding.root
    }

    private fun setUpSignUpButton(binding: FragmentRegistrationBinding) {
        with(binding.signUpButton) {
            isSelected = true
            setOnClickListener {
                isSelected = true
                binding.signInButton.isSelected = false
                navigate(binding, R.id.signUpFragment)
            }
        }
    }

    private fun setUpSignInButton(binding: FragmentRegistrationBinding) {
        with(binding.signInButton) {
            isSelected = false
            setOnClickListener {
                isSelected = true
                binding.signUpButton.isSelected = false
                navigate(binding, R.id.signInFragment)
            }
        }
    }

    private fun navigate(binding: FragmentRegistrationBinding, @IdRes id: Int) {
        with(binding.registrationFragmentContainer.findNavController()) {
            popBackStack()
            navigate(
                resId = id,
                args = null,
                navOptions = NavOptions.Builder()
                    .setEnterAnim(androidx.appcompat.R.anim.abc_fade_in)
                    .build()
            )
        }
    }

}