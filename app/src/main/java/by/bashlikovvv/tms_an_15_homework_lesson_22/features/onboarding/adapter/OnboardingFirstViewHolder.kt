package by.bashlikovvv.tms_an_15_homework_lesson_22.features.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.OnboardingFirstBinding

class OnboardingFirstViewHolder(
    private val binding: OnboardingFirstBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(onClickListener: () -> Unit) {
        binding.discoverButton.setOnClickListener { onClickListener() }
    }

    companion object {
        fun from(parent: ViewGroup): OnboardingFirstViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)

            return OnboardingFirstViewHolder(
                OnboardingFirstBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

}