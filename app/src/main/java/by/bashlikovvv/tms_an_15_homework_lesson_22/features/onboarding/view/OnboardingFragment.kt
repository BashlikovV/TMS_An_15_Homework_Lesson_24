package by.bashlikovvv.tms_an_15_homework_lesson_22.features.onboarding.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.bashlikovvv.tms_an_15_homework_lesson_22.R
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.FragmentOnboardingBinding
import by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model.OnboardingPagerItem
import by.bashlikovvv.tms_an_15_homework_lesson_22.features.onboarding.adapter.OnboardingPagerAdapter

class OnboardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        val adapter = OnboardingPagerAdapter {
            if (it == OnboardingPagerAdapter.ONBOARDING_FIRST) {
                binding.viewPager.adapter?.notifyItemMoved(0, 1)
            } else if (it == OnboardingPagerAdapter.ONBOARDING_FOURTH) {
                requireActivity().finish()
            }
        }
        adapter.submitList(onboardingData)
        binding.viewPager.adapter = adapter

        return binding.root
    }

    companion object {
        val onboardingData = listOf(
            OnboardingPagerItem(R.layout.onboarding_first),
            OnboardingPagerItem(R.layout.onboarding_second),
            OnboardingPagerItem(R.layout.onboarding_third),
            OnboardingPagerItem(R.layout.onboarding_fourth),
        )
    }

}