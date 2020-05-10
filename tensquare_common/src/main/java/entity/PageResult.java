package entity;

import lombok.Data;

import java.util.List;

/**
 *  * 分页结果类，可作为结果中的data数据
 * @param <T>
 */
@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
