package by.bashlikovvv.tms_an_15_homework_lesson_22.features.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.bashlikovvv.tms_an_15_homework_lesson_22.R
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.ActivityMainBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.Destination
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.setFragmentNavigationListener
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.App
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.view.ARG_PARAM_HEADER
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.view.ARG_PARAM_TEXT
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.view.ARG_PARAM_TIME
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.onboarding.view.OnboardingActivity
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.viewmodel.ApplicationViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val viewModel: ApplicationViewModel by viewModels {
        val app = applicationContext as App
        ApplicationViewModel.Factory(app.registrationRepository)
    }

    private val navController: NavController by lazy(LazyThreadSafetyMode.NONE) {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.mainFragmentContainerView) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onboardingActivityLauncher()
        registrationLauncher()

        supportFragmentManager.setFragmentNavigationListener(this) {
            fragmentNavigationListener(it)
        }
    }

    private fun onboardingActivityLauncher() {
        val prefs = getSharedPreferences(ONBOARDING_PREFERENCES_NAME, MODE_PRIVATE)
        if (prefs.getBoolean(KEY_OPENED, true)) {
            prefs
                .edit()
                .putBoolean(KEY_OPENED, false)
                .apply()
            startActivity(
                Intent(this, OnboardingActivity::class.java)
            )
        }
    }

    private fun registrationLauncher() {
        lifecycleScope.launch {
            viewModel.state.collectLatest {
                if (!it.isSignedIn) {
                    navController.popBackStack()
                    navController.navigate(R.id.registrationFragment)
                }
            }
        }
    }

    private fun fragmentNavigationListener(destination: Destination) {
        when (destination) {
            is Destination.Notes -> navController.navigate(R.id.notesFragment)
            is Destination.Registration -> {
                navController.apply {
                    popBackStack()
                    navigate(R.id.registrationFragment)
                }
            }
            is Destination.ReadNote -> {
                val note = destination.note
                var header = ""
                var text = ""
                var time = ""
                val formatter = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
                val calendar = Calendar.getInstance()
                when(note) {
                    is ItemCommon.NoteItem -> {
                        header = note.header
                        text = note.text
                        time = formatter.format(calendar.time)
                    }
                    is ItemCommon.ErrorItem -> {
                        header = note.header
                        text = note.text
                        time = formatter.format(calendar.time)
                    }
                }
                navController.navigate(
                    R.id.detailsFragment,
                    bundleOf(
                        ARG_PARAM_HEADER to header,
                        ARG_PARAM_TEXT to text,
                        ARG_PARAM_TIME to time
                    )
                )
            }
            is Destination.AddNote -> {
                navController.navigate(R.id.inputFragment)
            }
        }
    }

    companion object {
        const val ONBOARDING_PREFERENCES_NAME = "ONBOARDING_PREFERENCES"
        const val KEY_OPENED = "openedKey"
    }

}