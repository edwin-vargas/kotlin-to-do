// filepath: /kotlin-to-do/kotlin-to-do/src/main/kotlin/main.kt
import java.util.Scanner

// 1. Data class for a Task
data class Task(
    val id: Int,
    var description: String,
    var isComplete: Boolean = false
) {
    override fun toString(): String {
        val status = if (isComplete) "[X]" else "[ ]"
        return "$id. $status $description"
    }
}

// 2. TaskManager to handle operations
object TaskManager {
    private val tasks = mutableListOf<Task>()
    private var nextId = 1

    fun addTask(description: String) {
        if (description.isBlank()) {
            println("Error: Task description cannot be empty.")
            return
        }
        val task = Task(nextId++, description)
        tasks.add(task)
        println("Task added: $task")
    }

    fun listTasks(showAll: Boolean = true) {
        if (tasks.isEmpty()) {
            println("No tasks yet. Add some!")
            return
        }
        println("\n--- Your Tasks ---")
        tasks.forEach { println(it) }
        println("------------------")
    }

    fun findTaskById(id: Int): Task? {
        return tasks.find { it.id == id }
    }

    fun updateTask(id: Int, newDescription: String) {
        if (newDescription.isBlank()) {
            println("Error: New description cannot be empty.")
            return
        }
        val task = findTaskById(id)
        if (task != null) {
            task.description = newDescription
            println("Task updated: $task")
        } else {
            println("Error: Task with ID $id not found.")
        }
    }

    fun deleteTask(id: Int) {
        val task = findTaskById(id)
        if (task != null) {
            tasks.remove(task)
            println("Task deleted: $task")
        } else {
            println("Error: Task with ID $id not found.")
        }
    }

    fun markTaskComplete(id: Int, complete: Boolean = true) {
        val task = findTaskById(id)
        if (task != null) {
            task.isComplete = complete
            val action = if (complete) "completed" else "marked as incomplete"
            println("Task $action: $task")
        } else {
            println("Error: Task with ID $id not found.")
        }
    }
}

// 3. Main application loop
fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\n--- To-Do Menu ---")
        println("1. Add Task")
        println("2. List Tasks")
        println("3. Update Task Description")
        println("4. Delete Task")
        println("5. Mark Task as Complete")
        println("6. Mark Task as Incomplete")
        println("7. Exit")
        print("Choose an option: ")

        val choice = scanner.nextLine()?.trim()

        when (choice) {
            "1" -> {
                print("Enter task description: ")
                val description = scanner.nextLine() ?: ""
                TaskManager.addTask(description)
            }
            "2" -> {
                TaskManager.listTasks()
            }
            "3" -> {
                print("Enter ID of task to update: ")
                val id = scanner.nextLine()?.toIntOrNull()
                if (id != null) {
                    print("Enter new description: ")
                    val newDescription = scanner.nextLine() ?: ""
                    TaskManager.updateTask(id, newDescription)
                } else {
                    println("Invalid ID format.")
                }
            }
            "4" -> {
                print("Enter ID of task to delete: ")
                val id = scanner.nextLine()?.toIntOrNull()
                if (id != null) {
                    TaskManager.deleteTask(id)
                } else {
                    println("Invalid ID format.")
                }
            }
            "5" -> {
                print("Enter ID of task to mark as complete: ")
                val id = scanner.nextLine()?.toIntOrNull()
                if (id != null) {
                    TaskManager.markTaskComplete(id, true)
                } else {
                    println("Invalid ID format.")
                }
            }
            "6" -> {
                print("Enter ID of task to mark as incomplete: ")
                val id = scanner.nextLine()?.toIntOrNull()
                if (id != null) {
                    TaskManager.markTaskComplete(id, false)
                } else {
                    println("Invalid ID format.")
                }
            }
            "7" -> {
                println("Exiting To-Do App. Goodbye!")
                scanner.close()
                return
            }
            else -> {
                println("Invalid option. Please try again.")
            }
        }
    }
}