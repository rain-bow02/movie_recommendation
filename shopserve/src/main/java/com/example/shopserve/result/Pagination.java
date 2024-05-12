package com.example.shopserve.result;

import org.springframework.http.HttpStatus;

import java.util.List;

public class Pagination <T> {
    private List<T> data;
    private int total;
    private int page;
    private int size=10; // 每页最多返回的个数
    private int totalPage; // 每页最多返回的个数
    private int count; // 每页最多返回的个数



    public Pagination(List<T> data, int total, int page, int size,int totalPage,int count) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.size = size;
        this.totalPage =totalPage;
        this.count=count;

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        return (int) Math.ceil((double) total / size);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static<E> Pagination<E> ok(List<E> data,int total, int page){
        return new Pagination<>(data,total,page,10, (int) Math.ceil((double) total / 10),data.size());
    }
    public static<E> Pagination<E> ok(List<E> data,int total, int page, int size){
        return new Pagination<>(data,total,page,size, (int) Math.ceil((double) total / size),data.size());
    }

    public static<E> Pagination<E> error(int page){
        return new Pagination<>(null, 0, page,10,0,0);
    }

    public static<E> Pagination<E> error( int page, int size){
        return new Pagination<>(null, 0, page,size,0,0);
    }    // 构造器、getter和setter方法省略
}
