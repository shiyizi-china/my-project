package org.shiyizi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Barrage {
    private Integer id;
    private String content;
    private String username;
    private LocalDateTime createTime;
}