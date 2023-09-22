package chapter02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/9/22 23:31
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private String name;

    private String age;

    private PersonalAttr personalAttr;

}
