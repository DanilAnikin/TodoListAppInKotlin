import data.Database
import model.ToDoItemTypes
import worker.ToDoItemsWorker
import worker.UserChoiceProvider

fun main() {
    val userChoiceProvider = UserChoiceProvider()
    val database = Database()
    val toDoItemsWorker = ToDoItemsWorker()

    println("Привет!")
    while (true) {
        println("Выберите действие:")
        println(
            "1 - добавить запись\n" +
                    "2 - удалить запись\n" +
                    "3 - редактировать запись\n" +
                    "4 - посмотреть конкретную запись\n" +
                    "5 - посмотреть список всех записей\n" +
                    "6 - завершить работу программы"
        )
        when (userChoiceProvider.provideInRange(from = 1, to = 6)) {
            6 -> return
            1 -> {
                println("Выберите тип записи:")
                println(
                    "1 - с заголовком\n" +
                            "2 - с заголовком и текстом\n" +
                            "3 - с заголовком и картинкой\n" +
                            "4 - с заголовком, текстом и картинкой"
                )
                val toDoItemType = when (userChoiceProvider.provideInRange(from = 1, to = 4)) {
                    1 -> ToDoItemTypes.TODO_ITEM
                    2 -> ToDoItemTypes.TEXT_TODO_ITEM
                    3 -> ToDoItemTypes.IMAGE_TODO_ITEM
                    else -> ToDoItemTypes.TEXT_IMAGE_TODO_ITEM
                }
                val toDoItem = toDoItemsWorker.createToDoItem(toDoItemType = toDoItemType)
                database.addToDoItem(newToDoItem = toDoItem)
                println("Запись была успешно добавлена!")
            }
            2 -> {
                println("Введите id записи, которую хотите удалить")
                val id = userChoiceProvider.provideInRange()
                database.deleteToDoItem(id = id)?.let {
                    println("Запись была успешно удалена!")
                } ?: toDoItemsWorker.toDoNotFoundMessage()

            }
            3 -> {
                println("Введите id записи, которую хотите изменить")
                val id = userChoiceProvider.provideInRange()
                database.toDoItemsList[id]?.let {
                    toDoItemsWorker.updateToDoItem(it)
                    println("Запись была успешно изменена!")
                } ?: toDoItemsWorker.toDoNotFoundMessage()
            }
            4 -> {
                println("Введите id записи, которую хотите посмотреть")
                val id = userChoiceProvider.provideInRange()
                database.toDoItemsList[id]?.let {
                    println(it)
                } ?: toDoItemsWorker.toDoNotFoundMessage()
            }
            5 -> {
                database.toDoItemsList.values.forEach {
                    println(it)
                }
            }
        }
    }
}
