package by.bashlikovvv.tms_an_15_homework_lesson_22.features.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.OnboardingSecondBinding

class OnboardingSecondViewHolder(
    private val binding: OnboardingSecondBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {

    }

    companion object {
        fun from(parent: ViewGroup): OnboardingSecondViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)

            return OnboardingSecondViewHolder(
                OnboardingSecondBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

}