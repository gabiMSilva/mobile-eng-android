package com.gmsilva.basiccomponents

import android.os.Bundle
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val taskViewModel: TaskViewModel by viewModels()

    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TaskAdapter(this, taskViewModel.tasks.value ?: listOf())

        val tasksList = findViewById<ListView>(R.id.tasksList)
        tasksList.adapter = adapter

        taskViewModel.tasks.observe(this) {
            adapter.notifyDataSetChanged()
        }

        tasksList.setOnItemLongClickListener { _, _, position, _ ->
            taskViewModel.deleteTask(position)
            true
        }

        tasksList.setOnItemClickListener { _, _, position, _ ->
            taskViewModel.updateTask(position)
        }
    }
}