package com.mybatis;

public class Paging<T> {
    
    
    private int pageSize = 10;
    private long totalPage;
    private int offset;
    private T   param;
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public long getTotalPage() {
        return totalPage;
    }
    public long getPageNumber() {
        return totalPage%pageSize==0?totalPage/pageSize:totalPage/pageSize+1;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public T getParam() {
        return param;
    }
    public void setParam(T param) {
        this.param = param;
    }
    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
   
    
    
    
    
    
    
    

}
