package by.bashlikovvv.tms_an_15_homework_lesson_22.features.onboarding.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.bashlikovvv.tms_an_15_homework_lesson_22.R
import by.bashlikovvv.tms_an_15_homework_lesson_22.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity () {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.onboardingFragmentContainer, OnboardingFragment())
            .commit()
    }

}