package com.gmsilva.basiccomponents


class TaskNetworkLayer {
    private var tasks: MutableList<TaskModel> = mutableListOf()

    fun addTask(task: TaskModel): List<TaskModel> {
        tasks.add(task)
        return tasks
    }

    fun updateTask(position: Int): List<TaskModel> {
        tasks[position].isCompleted = !tasks[position].isCompleted
        return tasks
    }

    fun deleteTask(position: Int): List<TaskModel> {
        tasks.remove(tasks[position])
        return tasks
    }

    fun getTasks(): List<TaskModel> {
        return tasks
    }
}
