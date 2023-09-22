/**
 * @author 李旺
 * @version 1.0
 * @date 2023/7/9 09:02
 */
public enum Size {
    SMALL(0, "小"),
    MEDIUM(1, "中"),
    LARGE(2, "大");

    int code;

    String msg;

    Size(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
