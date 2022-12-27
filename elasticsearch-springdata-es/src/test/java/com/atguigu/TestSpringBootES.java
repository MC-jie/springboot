package com.atguigu;

import com.atguigu.dao.ItemRepository;
import com.atguigu.domain.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mr jie
 * @create 2022-19-26-11:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpringBootES {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void queryByPriceBetween(){
        List<Item> list = itemRepository.findByPriceBetween(2000.00, 3500.00);
        for (Item item : list) {
            System.out.println("item = " + item);
        }
    }

    /**
     * 查询全部
     */
    @Test
    public void testFill(){
        // 查询全部，并按照价格降序排序
        Iterable<Item> items = itemRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        for (Item item : items) {
            System.out.println(item);
        }
    }

    /**
     * 查询
     */
    @Test
    public void testQuery(){
        Optional<Item> optional = itemRepository.findById(3L);
        System.out.println(optional.get());
    }

    /**
     * 删除
     */
    @Test   
    public void testDelete(){
        itemRepository.deleteById(1L);
    }
    /**
     * 批量新增
     */
    @Test
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    /**
     * 修改(id存在就是修改，否则就是插入)
     */
    @Test
    public void testUpdate(){
        Item item = new Item(1L, "小米手机7777", " 手机",
                "小米", 31211.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);
    }

    /**
     * 新增
     */
    @Test
    public void testAdd(){
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);
    }

    @Test
    public void testEsCreate(){
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Item.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(Item.class);
    }
}
