package com.volunteer.util;

public class PageUtil {
    private int currentPage;
    private int pageSize;
    private int totalRecords;
    private int totalPages;
    
    public PageUtil(int currentPage, int pageSize, int totalRecords) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.totalPages = (totalRecords + pageSize - 1) / pageSize;
        if (this.currentPage < 1) this.currentPage = 1;
        if (this.currentPage > totalPages && totalPages > 0) this.currentPage = totalPages;
    }
    
    public int getCurrentPage() { return currentPage; }
    public int getPageSize() { return pageSize; }
    public int getTotalRecords() { return totalRecords; }
    public int getTotalPages() { return totalPages; }
    public int getOffset() { return (currentPage - 1) * pageSize; }
}
