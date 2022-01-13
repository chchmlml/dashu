package io.haicheng.stockmonitorcollection.domain.dto;

/**
 * <p>Title: Result</p>
 * <p>Description: </p>
 * ----------------------------
 *
 * @author haicheng
 * @date 2022/1/13 18:44
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * JSON信息交互对象模板
 *
 * @Author Autumn、
 * @Date 2019/4/8 23:46
 * @Description
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Result {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;
    /**
     * 请求地址
     */
    private String url;

    public static Result succeed(Object data) {
        return new Result(0, "everythind is ok", data, "");
    }

}