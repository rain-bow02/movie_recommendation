package com.example.shopserve.result;

import org.springframework.http.HttpStatus;

import java.util.List;

public class Pagination <T> {
    private List<T> data;
    private int total;
    private int page;
    private int size=10; // 每页最多返回的个数

    public Pagination(List<T> data, int total, int page, int size) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.size = size;
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

    public static<E> Pagination<E> ok(List<E> data, int page){
        return new Pagination<>(data,data.size(),page,10);
    }
    public static<E> Pagination<E> ok(List<E> data, int page, int size){
        return new Pagination<>(data,data.size(),page,size);
    }

    public static<E> Pagination<E> error(int page){
        return new Pagination<>(null, 0, page,10);
    }

    public static<E> Pagination<E> error( int page, int size){
        return new Pagination<>(null, 0, page,size);
    }    // 构造器、getter和setter方法省略
}
