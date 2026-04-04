package org.shiyizi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class image {

    private Integer id;

    private String fileUrl;

    private LocalDateTime createTime;
}
