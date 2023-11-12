package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.notes.ApplicationData
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentNotesBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.Destination
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.navigateToDestination
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.App
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.adapter.NotesListAdapter
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.viewmodel.NotesFragmentViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotesFragment : Fragment() {

    private val viewModel: NotesFragmentViewModel by viewModels {
        val app = requireActivity().applicationContext as App
        NotesFragmentViewModel.Factory(app.registrationRepository, ApplicationData)
    }

    private val adapter: NotesListAdapter by lazy {
        NotesListAdapter { viewModel.openNote(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNotesBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collectLatest {
                    adapter.submitList(it.notes)
                    if (it.onNoteClicked) {
                        navigateToDestination(Destination.ReadNote(it.note))
                    }
                    if (it.onAddNoteClicked) {
                        navigateToDestination(Destination.AddNote)
                    }
                }
            }
        }

        binding.notesRecyclerView.adapter = adapter

        binding.logOutButton.setOnClickListener {
            viewModel.logOut()
            navigateToDestination(Destination.Registration)
        }
        binding.fab.setOnClickListener {
            viewModel.addNote()
        }

        return binding.root
    }

}