package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.viewmodel.InputFragmentViewModel
import by.bashlikovvv.tms_an_15_homework_lesson_22.R
import by.bashlikovvv.tms_an_15_homework_lesson_22.data.notes.ApplicationData
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentInputBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.Destination
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.ItemCommon
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.navigateToDestination
import java.util.Calendar

const val TAG_NOTES_FRAGMENT = "fragmentNotes"
const val TAG_DETAILS_FRAGMENT = "fragmentDetails"
const val TAG_INPUT_FRAGMENT = "fragmentInput"

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding

    private val viewModel: InputFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)

        binding.headerCB.setOnClickListener {
            onHeaderCheckBoxClickListener(it, binding)
        }
        binding.textCB.setOnClickListener {
            it as CheckBox
            if (it.isChecked) {
                binding.textInputET.setText(R.string.lorem_ipsum)
            } else {
                binding.textInputET.setText("")
            }
        }

        binding.submitBtn.setOnClickListener { onSubmit() }

        return binding.root
    }

    private fun onSubmit() {
        binding.createNote()
        viewModel.state.observe(viewLifecycleOwner) {
            ApplicationData.addNote(it.note)
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            navigateToDestination(Destination.Notes)
        }
    }

    private fun FragmentInputBinding.createNote() {
        val calendar = Calendar.getInstance()
        val header = headerInputET.text.toString()
        val text = textInputET.text.toString()
        val note = if (importantCB.isChecked) {
            ItemCommon.ErrorItem(
                header, text, calendar.timeInMillis
            )
        } else {
            ItemCommon.NoteItem(
                header, text, calendar.timeInMillis
            )
        }

        viewModel.setNote(note)
    }

    private fun onHeaderCheckBoxClickListener(
        it: View?,
        binding: FragmentInputBinding
    ) {
        it as CheckBox
        if (it.isChecked) {
            binding.headerInputET.setText(R.string.header_text)
        } else {
            binding.headerInputET.setText("")
        }
    }

}