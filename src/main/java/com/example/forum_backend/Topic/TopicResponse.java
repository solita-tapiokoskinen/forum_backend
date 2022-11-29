package com.example.forum_backend.Topic;

import java.util.List;

public class TopicResponse {

    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private List<TopicDto> content;
    private int pageNo;

    public TopicResponse () {}

    public List<TopicDto> getContent() {
        return content;
    }

    public void setContent(List<TopicDto> content) {
        this.content = content;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

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

    public TopicResponse(List<TopicDto> content, int pageNo, int pageSize, long totalElements, int totalPages, boolean last) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }


    @Override
    public String toString() {
        return "TopicResponse{" +
                "content=" + content +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", last=" + last +
                '}';
    }


}
