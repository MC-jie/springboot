package com.atguigu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author Mr jie
 * @create 2022-56-25-23:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "item",shards = 1,replicas = 1)
public class Item implements Serializable {
    @Id
    private Long id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;//标题

    @Field(type = FieldType.Keyword)
    private String category;//分类

    @Field(type = FieldType.Keyword)
    private String brand;// 品牌

    @Field(type = FieldType.Double)
    private Double price; // 价格

    @Field(index = false,type = FieldType.Keyword)
    private String images; // 图片地址
}

