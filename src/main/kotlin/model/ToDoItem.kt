package model

open class ToDoItem(
    var id: Int,
    var title: String,
    var creationDate: String
) {
    override fun toString(): String {
        return "$title\t$creationDate\tid=$id\n"
    }
}