package by.bashlikovvv.tms_an_15_homework_lesson_22.features.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.OnboardingThirdBinding

class OnboardingThirdViewHolder(
    private val binding: OnboardingThirdBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {

    }

    companion object {
        fun from(parent: ViewGroup): OnboardingThirdViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)

            return OnboardingThirdViewHolder(
                OnboardingThirdBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

}