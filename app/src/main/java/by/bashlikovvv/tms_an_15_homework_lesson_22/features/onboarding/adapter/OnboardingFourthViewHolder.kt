package by.bashlikovvv.tms_an_15_homework_lesson_22.features.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.OnboardingFourthBinding

class OnboardingFourthViewHolder(
    private val binding: OnboardingFourthBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(onSkipClickListener: () -> Unit) {
        binding.skipButton.setOnClickListener { onSkipClickListener() }
    }

    companion object {
        fun from(parent: ViewGroup): OnboardingFourthViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)

            return OnboardingFourthViewHolder(
                OnboardingFourthBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

}