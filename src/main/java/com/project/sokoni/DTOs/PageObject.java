package com.project.sokoni.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PageObject {

    private Integer pageNumber;

    private Integer pageSize;
}
