package com.mjdev.taskorganizer.data.models

enum class SortTypes(val displayStr: String) {
    TITLE_ASC("Title Ascending"),
    TITLE_DESC("Title Descending"),
    DATE_CREATED_ASC("Date created ascending"),
    DATE_CREATED_DESC("Date created descending"),
    DATE_MODIFIED_ASC("Date modified ascending"),
    DATE_MODIFIED_DESC("Date modified descending"),
    DATE_COMPLETION_ASC("Date completed ascending"),
    DATE_COMPLETION_DESC("Date completed descending"),
    DEADLINE_ASC("Deadline ascending"),
    DEADLINE_DESC("Deadline descending")
//    DEADLINE_ASC,
//    DEADLINE_DESC,
//    COMPLETED_ASC,
//    COMPLETED_DESC,
//    CREATED_ASC,
//    CREATED_DESC
}