package by.bashlikovvv.tms_an_15_homework_lesson_22.features.notes.domain.model

class LocalChanges {

    private val idsInProgress = mutableSetOf<Int>()
    private val selectedFlags = mutableMapOf<Int, Boolean>()

    fun selectItem(id: Int) {
        selectedFlags.merge(id, selectedFlags[id] ?: true) { _, _ ->
            selectedFlags[id]?.not() ?: true
        }
    }

    fun isSelected(id: Int) = selectedFlags[id]

    fun unselect() = selectedFlags.clear()

    fun isInProgress(id: Int): Boolean = idsInProgress.contains(id)

    fun isContainsSelected() = selectedFlags.containsValue(true)

}