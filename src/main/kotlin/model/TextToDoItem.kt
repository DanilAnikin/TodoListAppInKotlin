package model

open class TextToDoItem(
    id: Int,
    title: String,
    creationDate: String,
    var text: String
): ToDoItem(
    id = id,
    title = title,
    creationDate = creationDate
) {
    override fun toString(): String {
        return super.toString() + "$text\n"
    }
}