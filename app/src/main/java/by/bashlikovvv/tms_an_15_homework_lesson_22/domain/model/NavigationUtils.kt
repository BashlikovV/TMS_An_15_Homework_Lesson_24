package by.bashlikovvv.tms_an_15_homework_lesson_22.domain.model

import android.os.Build
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner

const val REQ_KEY_MAIN_DESTINATION = "mainNavigationResult"

private const val PARAM_DATA = "bundleData"
/**
 * Indicates the desire to open a different destination
 */
fun Fragment.navigateToDestination(destination : Destination) {
    requireActivity()
        .supportFragmentManager
        .setFragmentResult(
            REQ_KEY_MAIN_DESTINATION,
            bundleOf(PARAM_DATA to destination)
        )
}

/**
 * Some sugar to simplify how the fragment listener is set. In this way we use a fixed key for navigation events while the fragment can still use Result API
 * to deliver other results which are not related to navigation
 */
fun FragmentManager.setFragmentNavigationListener(
    lifecycleOwner: LifecycleOwner,
    listener: (destination : Destination) -> Any
) {
    setFragmentResultListener(REQ_KEY_MAIN_DESTINATION, lifecycleOwner) { _, bundle ->
        val destination = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(PARAM_DATA, Destination::class.java)!!
        } else {
            bundle.getParcelable(PARAM_DATA)!!
        }
        listener.invoke(destination)
    }
}