package org.shiyizi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deity {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String gender;
    private String Clazz;
    private String birthday;
    private String phone;
    private String token;
    private String avatar;
}