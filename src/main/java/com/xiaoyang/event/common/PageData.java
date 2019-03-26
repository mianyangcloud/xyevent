package com.xiaoyang.event.common;

import java.io.Serializable;
import java.util.List;

public class PageData<T extends Serializable> implements Serializable {

    public static final int DEFAULT_PAGE_SIZE = 10;
    
    public static final int DEFAULT_PAGE_NUM = 1;
    
    private static final long serialVersionUID = 9006197747338201674L;
    
    protected int totalCount;
    
    protected int pageSize;
    
    protected int totalPage;
    
    protected int currentPage;
    
    protected int nextPage;
    
    protected int previousPage;
    
    protected boolean hasNext;
    
    protected boolean hasPrevious;
    
    protected List<T> list;
    
    protected int startIndex;

    public PageData() {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.hasNext = false;
        this.hasPrevious = false;
    }

    public PageData(int currentPage, int pageSize) {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.hasNext = false;
        this.hasPrevious = false;
        if(currentPage <= 0) {
            currentPage = 1;
        }
        if(pageSize <= 0) {
            pageSize = 20;
        }
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.startIndex = (currentPage - 1) * pageSize;
    }

    public PageData(int totalCount) {
        this(totalCount, totalCount);
    }

    public PageData(int totalCount, int pageSize, int currentPage) {
        this(totalCount, pageSize, currentPage, 9);
    }

    public PageData(int totalCount, int pageSize, int currentPage, int maxPageIndexNumber) {
        this.hasNext = false;
        this.hasPrevious = false;
        this.init(totalCount, pageSize, currentPage);
    }

    private void calculatePage() {
        if(this.totalCount % this.pageSize == 0) {
            this.totalPage = this.totalCount / this.pageSize;
        } else {
            this.totalPage = this.totalCount / this.pageSize + 1;
        }
        this.hasPrevious = this.currentPage - 1 > 0;
        this.hasNext = this.currentPage < this.totalPage;
        if(this.hasPrevious) {
            this.previousPage = this.currentPage - 1;
        }
        if(this.hasNext) {
            this.nextPage = this.currentPage + 1;
        }
    }

    public void init(int totalCount, int pageSize, int currentPage) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.calculatePage();
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public boolean isHasNext() {
        return this.hasNext;
    }

    public boolean isHasPrevious() {
        return this.hasPrevious;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public boolean hasPrevious() {
        return this.hasPrevious;
    }

    public int getPreviousPage() {
        return this.previousPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getHasPrevious() {
        return this.hasPrevious()?"true":"false";
    }

    public String getHasNext() {
        return this.hasNext()?"true":"false";
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setHasNext(String s) {
        this.hasNext = s == "true";
    }

    public void setHasPrevious(String s) {
        this.hasPrevious = s == "true";
    }

    public void setTotalPage(int size) {
        this.totalPage = size;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

}
