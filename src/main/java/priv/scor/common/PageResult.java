package priv.scor.common;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @createBy Edgar
 * @date 2018/5/24
 * @Package_name priv.scor.common
 * @desc 封装分页
 */
public class PageResult<T> extends Result<T>{

    @ApiModelProperty(value = "第几页，默认第1页")
    private int pageIndex = 1;

    @ApiModelProperty(value = "每页显示几条数据，默认10")
    private int pageSize = 10;

    @ApiModelProperty(value = "总记录数")
    private long total = 0L;

    @ApiModelProperty(value = "排序字段")
    private List<Sort> sorts;

    public PageResult(){}

    public PageResult(PageResult<T> result){
        this.pageIndex = result.getPageIndex();
        this.pageSize = result.getPageSize();
        this.sorts = result.getSorts();
        this.total = result.getTotal();
    }


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }
}
