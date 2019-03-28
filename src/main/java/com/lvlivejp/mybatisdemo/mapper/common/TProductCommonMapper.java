package com.lvlivejp.mybatisdemo.mapper.common;

import com.lvlivejp.mybatisdemo.model.TProduct;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TProductCommonMapper extends Mapper<TProduct> {

}
