package com.atguigu.dao;

import com.atguigu.domain.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author Mr jie
 * @create 2022-26-26-11:26
 */
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    /**
     * 根据价格区间查询
     * @param price1
     * @param price2
     * @return
     */
    List<Item> findByPriceBetween(double price1,double price2);
}
