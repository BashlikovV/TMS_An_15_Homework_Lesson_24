package by.bashlikovvv.tms_an_15_homework_lesson_22.features.onboarding.adapter

import androidx.recyclerview.widget.DiffUtil
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.OnboardingPagerItem

class OnboardingPagerItemDiffCallback : DiffUtil.ItemCallback<OnboardingPagerItem>() {
    override fun areItemsTheSame(
        oldItem: OnboardingPagerItem,
        newItem: OnboardingPagerItem
    ): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: OnboardingPagerItem,
        newItem: OnboardingPagerItem
    ): Boolean {
        return oldItem == newItem
    }
}