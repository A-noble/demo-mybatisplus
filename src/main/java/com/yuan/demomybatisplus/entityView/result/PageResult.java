package com.yuan.demomybatisplus.entityView.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * @author MuXue
 * @create 2022-07-11  22:24 PM
 */
@Data
public class PageResult <T>{
    /**
     * 数据总数
     */
    private long count;

    /**
     * 数据记录
     */
    private List<T> records;

    /***
     * 总页数
     */
    private long pages;

    public PageResult(IPage<T> page) {
        this.count = page.getTotal();
        this.records = page.getRecords();
        this.pages = count % page.getSize() == 0 ? count / page.getSize() : count / page.getSize() + 1;
    }

    /**
     * 分页封装到这里
     * @param list
     * @param count
     */
    public PageResult(List<T> list, Long count, long size) {
        this.count = count;
        this.records = list;
        this.pages = count % size == 0 ? count / size : count / size + 1;
    }


}
