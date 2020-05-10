package entity;

import lombok.Data;

import java.util.List;

/**
 * 结果返回类，resultfull风格
 */
@Data
public class Result {
    private boolean flag; //是否成功
    private Integer code; //返回码（状态码）
    private String message; //返回信息
    private Object data; //返回数据

    public Result(boolean flag, Integer code, String message, Object
            data) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public Result(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
