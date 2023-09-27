package com.mjdev.taskorganizer.data.models

enum class FilterTypes (val displayStr: String, val category: String) {
    SHOW_ALL_TASKS_COMPLETION("Show all", "TASK_COMPLETION"),
    SHOW_INCOMPLETE_TASKS("Incomplete only", "TASK_COMPLETION"),
    SHOW_COMPLETE_TASKS("Completed only", "TASK_COMPLETION"),
    SHOW_ALL_TASKS_DEADLINE("Show all", "TASK_DEADLINE"),
    SHOW_TASKS_NO_DEADLINE("No deadline", "TASK_DEADLINE"),
    SHOW_TASKS_WITH_DEADLINE("With deadline", "TASK_DEADLINE"),
}