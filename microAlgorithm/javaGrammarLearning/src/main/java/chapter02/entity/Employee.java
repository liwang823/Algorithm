package chapter02.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 李旺
 * @version 1.0
 * @date 2023/9/23 22:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @JsonProperty("名称")
    String name;

    @JsonProperty("年龄")
    int age;

    @JsonProperty("分数")
    double score;
}
