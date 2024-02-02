package com.service;

import lombok.Data;

@Data
public class PlaySubmissionRequest {
    private Integer playId;
    private String playSet;
    private int playTime;
}
