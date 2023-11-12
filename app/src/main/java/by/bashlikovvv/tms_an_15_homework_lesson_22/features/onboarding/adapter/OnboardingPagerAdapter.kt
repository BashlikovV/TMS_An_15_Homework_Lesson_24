package by.bashlikovvv.tms_an_15_homework_lesson_22.features.onboarding.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.OnboardingPagerItem

class OnboardingPagerAdapter(
    private val onClickListener: (viewType: Int) -> Unit
) : ListAdapter<OnboardingPagerItem, RecyclerView.ViewHolder>(OnboardingPagerItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ONBOARDING_FIRST -> OnboardingFirstViewHolder.from(parent)
            ONBOARDING_SECOND -> OnboardingSecondViewHolder.from(parent)
            ONBOARDING_THIRD -> OnboardingThirdViewHolder.from(parent)
            ONBOARDING_FOURTH -> OnboardingFourthViewHolder.from(parent)
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is OnboardingFirstViewHolder -> holder.bind { onClickListener(ONBOARDING_FIRST) }
            is OnboardingSecondViewHolder -> holder.bind()
            is OnboardingThirdViewHolder -> holder.bind()
            is OnboardingFourthViewHolder -> holder.bind { onClickListener(ONBOARDING_FOURTH) }
            else -> throw IllegalStateException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    companion object {
        const val ONBOARDING_FIRST = 0
        const val ONBOARDING_SECOND = 1
        const val ONBOARDING_THIRD = 2
        const val ONBOARDING_FOURTH = 3
    }

}