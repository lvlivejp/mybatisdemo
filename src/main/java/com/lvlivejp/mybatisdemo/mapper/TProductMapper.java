package com.lvlivejp.mybatisdemo.mapper;

import com.lvlivejp.mybatisdemo.model.TProduct;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TProductMapper {
    int insert(TProduct tProduct);

    TProduct selectById(String productCode);

    /**
     * 可以返回一个以MapKey指定字段的值为key，实体类为Value的Map
     * 例如：key=1(productCode)，value=TProduct
     */
    @MapKey("productCode")
    Map<String,TProduct> selectReturnMap();

    List<TProduct> selectReturnList();

    /**
     * 用where和if标签查询动态条件
     * @param tProduct
     * @return
     */
    List<TProduct> selectByIfAndWhere(TProduct tProduct);

    /**
     * 用Set和If标签实现动态字段更新
     * @param tProduct
     * @return
     */
    Integer updateBySetAndIf(TProduct tProduct);

    /**
     * 用Foreach标签实现in的多个值匹配
     * @param ids
     * @return
     */
    List<TProduct> selectByForeach(@Param("ids") List<Integer> ids);

    Integer batchInsertByForeach(@Param("products") List<TProduct> products);
}
