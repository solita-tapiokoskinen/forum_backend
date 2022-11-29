package com.example.forum_backend.Comment;

import java.util.List;

public class CommentResponse {

    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private List<CommentDto> content;
    private int pageNo;

    public CommentResponse() {}

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<CommentDto> getContent() {
        return content;
    }

    public void setContent(List<CommentDto> content) {
        this.content = content;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public CommentResponse(int pageSize, long totalElements, int totalPages, boolean last, List<CommentDto> content, int pageNo) {
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
        this.content = content;
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "CommentResponse{" +
                "pageSize=" + pageSize +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                ", content=" + content +
                ", pageNo=" + pageNo +
                '}';
    }



}
