package com.DTO;

import com.model.Task;

import lombok.Data;

@Data
public class TaskResponse {
    private Task task;
    private boolean status;
    private Long id;

    public TaskResponse(Task task,Long id, boolean status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

}

