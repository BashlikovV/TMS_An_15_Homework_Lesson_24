package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.bashlikovvv.tms_an_15_homework_lesson_22.R
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentDetailsBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.viewmodel.DetailsFragmentViewModel

const val ARG_PARAM_HEADER = "paramHeader"
const val ARG_PARAM_TEXT = "paramText"
const val ARG_PARAM_TIME = "paramTime"
private const val DEFAULT_VALUE = "error"

class DetailsFragment : Fragment() {

    private val viewModel: DetailsFragmentViewModel by viewModels()

    private var header: String? = null
    private var text: String? = null
    private var time: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            header = it.getString(ARG_PARAM_HEADER) ?: DEFAULT_VALUE
            text = it.getString(ARG_PARAM_TEXT) ?: DEFAULT_VALUE
            time = it.getString(ARG_PARAM_TIME) ?: DEFAULT_VALUE
        }
        viewModel.setValues(header ?: "", time ?: "", text ?: "")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        viewModel.state.observe(viewLifecycleOwner) {
            with(binding) {
                timeTV.text = it.timeText
                headerTV.text = it.headerText
                textTV.text = it.text
            }
        }

        return binding.root
    }

}