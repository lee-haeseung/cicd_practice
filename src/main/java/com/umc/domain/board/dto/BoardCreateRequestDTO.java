package com.umc.domain.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardCreateRequestDTO {
    private String title;
    private String description;
}
