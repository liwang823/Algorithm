package chapter02.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/9/23 00:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalAttr implements Serializable {

    private String company;

    private String position;
}
