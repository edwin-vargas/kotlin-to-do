import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun testAddTask() {
        TaskManager.addTask("Test Task")
        assertEquals(1, TaskManager.listTasks().size)
        assertEquals("Test Task", TaskManager.listTasks()[0].description)
    }

    @Test
    fun testUpdateTask() {
        TaskManager.addTask("Old Task")
        TaskManager.updateTask(1, "Updated Task")
        assertEquals("Updated Task", TaskManager.findTaskById(1)?.description)
    }

    @Test
    fun testDeleteTask() {
        TaskManager.addTask("Task to Delete")
        TaskManager.deleteTask(1)
        assertNull(TaskManager.findTaskById(1))
    }

    @Test
    fun testMarkTaskComplete() {
        TaskManager.addTask("Task to Complete")
        TaskManager.markTaskComplete(1)
        assertTrue(TaskManager.findTaskById(1)?.isComplete ?: false)
    }

    @Test
    fun testMarkTaskIncomplete() {
        TaskManager.addTask("Task to Incomplete")
        TaskManager.markTaskComplete(1)
        TaskManager.markTaskComplete(1, false)
        assertFalse(TaskManager.findTaskById(1)?.isComplete ?: true)
    }
}