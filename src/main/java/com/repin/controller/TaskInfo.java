package com.repin.controller;

import com.repin.domain.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInfo {
    private String description;
    private Status status;
}