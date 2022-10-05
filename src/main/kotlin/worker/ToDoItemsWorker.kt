package worker

import model.*
import model.ToDoItemTypes.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ToDoItemsWorker {
    private val userChoiceProvider = UserChoiceProvider()
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm")

    fun createToDoItem(toDoItemType: ToDoItemTypes) : ToDoItem {
        idCounter++
        val currentDate = provideCurrentDate()
        print("Введите заголовок: ")
        val title = readLine()!!
        return when (toDoItemType) {
            TODO_ITEM -> {
                ToDoItem(id = idCounter, title = title, creationDate = currentDate)
            }
            TEXT_TODO_ITEM -> {
                print("Введите текст: ")
                val text = readLine()!!
                TextToDoItem(id = idCounter, title = title, creationDate = currentDate, text = text)
            }
            IMAGE_TODO_ITEM -> {
                print("Введите URL картинки: ")
                val url = readLine()!!
                ImageToDoItem(id = idCounter, title = title, creationDate = currentDate, imageUrl = url)
            }
            TEXT_IMAGE_TODO_ITEM -> {
                print("Введите текст: ")
                val text = readLine()!!
                print("Введите URL картинки: ")
                val url = readLine()!!
                TextImageToDoItem(id = idCounter, title = title, creationDate = currentDate, text = text, imageUrl = url)
            }
        }
    }

    fun updateToDoItem(toDoItem: ToDoItem) {
        if(shouldChangeField(fieldToChange = "заголовок")) {
            print("Введите новый заголовок: ")
            toDoItem.title = readLine()!!
        }
        when(toDoItem) {
            is TextToDoItem -> {
                if(shouldChangeField(fieldToChange = "текст")) {
                    print("Введите новый текст: ")
                    toDoItem.text = readLine()!!
                }
                if(toDoItem is TextImageToDoItem) {
                    if(shouldChangeField(fieldToChange = "картинку")) {
                        print("Введите URL новой картинки: ")
                        toDoItem.imageUrl = readLine()!!
                    }
                }
            }
            is ImageToDoItem -> {
                if(shouldChangeField(fieldToChange = "картинку")) {
                    print("Введите URL новой картинки: ")
                    toDoItem.imageUrl = readLine()!!
                }
            }
        }
        toDoItem.creationDate = provideCurrentDate()
    }

    private fun shouldChangeField(fieldToChange: String): Boolean {
        println("Изменить $fieldToChange?")
        println("1 - да\n2 - нет")
        val editChoice = userChoiceProvider.provideInRange(1, 2)
        return editChoice == 1
    }

    private fun provideCurrentDate() = LocalDateTime.now().format(formatter)

    fun toDoNotFoundMessage() {
        println("Запись не найдена")
    }

    companion object {
        var idCounter = -1
    }
}