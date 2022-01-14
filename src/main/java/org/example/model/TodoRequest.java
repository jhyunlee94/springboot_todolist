package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //entity는 아니기에 따로 안붙힘
public class TodoRequest {

    private String title;
    private Long order;
    private Boolean completed;
}
