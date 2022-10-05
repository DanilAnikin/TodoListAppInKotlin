package model

class ImageToDoItem(
    id: Int,
    title: String,
    creationDate: String,
    var imageUrl: String
) : ToDoItem(
    id = id,
    title = title,
    creationDate = creationDate
) {
    override fun toString(): String {
        return super.toString() + "$imageUrl\n"
    }
}