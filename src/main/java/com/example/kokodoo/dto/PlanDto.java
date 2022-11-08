package com.example.kokodoo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {
    private List<String> foodAndPlace;
}
