package model

class TextImageToDoItem(
    id: Int,
    title: String,
    creationDate: String,
    text: String,
    var imageUrl: String
) : TextToDoItem(
    id = id,
    title = title,
    creationDate = creationDate,
    text = text
) {
    override fun toString(): String {
        return super.toString() + "$imageUrl\n"
    }
}