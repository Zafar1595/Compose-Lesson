package uz.domain.composelesson.ui

class MainViewModel {

    fun getItems(): List<String> {
        val items = mutableListOf<String>()
        for (i in 0..100) {
            items.add("Person Full Name $i")
        }
        return items
    }

}