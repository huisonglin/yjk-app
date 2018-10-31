package com.yjk.app.util;

/*
 * 分页信息类
 */
public class Page {
    // 每页显示记录数
    private int everyPage;
    // 总页数
    private int totalPage;
    // 总记录数
    private int totalCount;
    // 当前页
    private int currentPage;
    // 起始点
    private int beginIndex;
    // 是否有上一页
    private boolean hasPrePage;
    // 是否有下一页
    private boolean hasNextPage;

    //默认构造函数
    public Page() {
    }

    //构造函数
    public Page(int everyPage, int totalPage, int totalCount, int currentPage, int beginIndex, boolean hasPrePage,
            boolean hasNextPage) {
        this.everyPage = everyPage;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.beginIndex = beginIndex;
        this.hasPrePage = hasPrePage;
        this.hasNextPage = hasNextPage;
    }

    public int getEveryPage() {
        return everyPage;
    }

    public void setEveryPage(int everyPage) {
        this.everyPage = everyPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

}
