package com.ssafy.choonong.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class SurveyRequest {
    private int educationCluster;
    private int groundCluster;
    private int residentCluster;
    private int envCluster;
}
