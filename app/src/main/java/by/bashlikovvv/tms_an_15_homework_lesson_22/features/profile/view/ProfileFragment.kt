package by.bashlikovvv.tms_an_15_homework_lesson_22.features.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentProfileBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.profile.viewmodel.ProfileFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.initState()
            }
        }
        lifecycleScope.launch {
            viewModel.state.observe(viewLifecycleOwner) { currentUserInfo ->
                with(binding) {
                    emailTextView.text = currentUserInfo.email
                    hashTextView.text = currentUserInfo.hash
                    saltTextView.text = currentUserInfo.salt
                    timeTextView.text = currentUserInfo.lasConnectionTime.toString()
                }
            }
        }

        return binding.root
    }
}