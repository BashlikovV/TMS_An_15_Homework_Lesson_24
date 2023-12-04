package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.bashlikovvv.tms_an_15_homework_lesson_22.R
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentNotesBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.Destination
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.navigateToDestination
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.adapter.NotesListAdapter
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.viewmodel.NotesFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private val viewModel: NotesFragmentViewModel by viewModels()

    private val adapter: NotesListAdapter by lazy {
        NotesListAdapter { viewModel.openNote(it) }
    }

    private var _searchView: androidx.appcompat.widget.SearchView? = null
    private val searchView get() = _searchView!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNotesBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collectLatest {
                    if (it.onNoteClicked) {
                        navigateToDestination(Destination.ReadNote(it.note))
                    }
                    if (it.onAddNoteClicked) {
                        navigateToDestination(Destination.AddNote)
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.notes.collectLatest {
                adapter.submitList(it)
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

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolBar)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(menuProvider(), viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    private fun menuProvider() = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.search_menu, menu)
            val item: MenuItem? = menu.findItem(R.id.action_search)
            if (item != null) {
                _searchView = item.actionView as androidx.appcompat.widget.SearchView
                setUpSearchView(searchView)
            }
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return when (menuItem.itemId) {
                R.id.action_search -> { true }
                else -> { false }
            }
        }
    }

    private fun setUpSearchView(menuItem: androidx.appcompat.widget.SearchView) {
        with(menuItem) {
            setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.onSearchTextChange(query ?: "")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.onSearchTextChange(newText ?: "")
                    return true
                }
            })
            setOnSearchClickListener {
//                viewModel.unselectPokemon()
            }
        }
    }
}