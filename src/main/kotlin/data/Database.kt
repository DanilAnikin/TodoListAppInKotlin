package data

import model.ToDoItem

class Database {
    val _toDoItemsList = mutableMapOf<Int, ToDoItem>()
    val toDoItemsList get() = _toDoItemsList

    fun addToDoItem(newToDoItem: ToDoItem) {
        _toDoItemsList[newToDoItem.id] = newToDoItem
    }

    fun deleteToDoItem(id: Int): ToDoItem? {
        return _toDoItemsList.remove(id)
    }
}