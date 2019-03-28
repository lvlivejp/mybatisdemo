package com.lvlivejp.mybatisdemo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lvlivejp.mybatisdemo.mapper.TBuyInfoMapper;
import com.lvlivejp.mybatisdemo.mapper.TProductMapper;
import com.lvlivejp.mybatisdemo.mapper.common.TProductCommonMapper;
import com.lvlivejp.mybatisdemo.model.TBuyInfo;
import com.lvlivejp.mybatisdemo.model.TProduct;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisdemoApplicationTests {

    @Autowired(required = false)
    private TProductMapper tProductMapper;

    @Autowired(required = false)
    private TBuyInfoMapper tBuyInfoMapper;

    @Autowired(required = false)
    private TProductCommonMapper tProductCommonMapper;

    @Test
    public void contextLoads() {
        System.out.println(tProductMapper.selectById("1"));
    }

    /**
     * 测试insert主键返回值
     */
    @Test
    public void insertTest(){
        TProduct tProduct = new TProduct("name", null, null, 100);
        System.out.println(tProductMapper.insert(tProduct));
        System.out.println(tProduct);
    }

    /**
     * 通用测试insert主键返回值
     */
    @Test
    public void insertTestByCommonMapper(){
        TProduct tProduct = new TProduct("name", null, null, 100);
        System.out.println(tProductCommonMapper.insert(tProduct));
        System.out.println(tProduct);
    }

    /**
     * 返回Map集合，key为指定主键，value为实体类
     */
    @Test
    public void selectReturnMap() {
        System.out.println(tProductMapper.selectReturnMap());
    }

    @Test
    public void selectReturnList() {
        System.out.println(tProductMapper.selectReturnList());
    }

    /**
     * 级联测试1
     */
    @Test
    public void selectBuyInfoAndProductById_1() {
        TBuyInfo tBuyInfo=new TBuyInfo();
        tBuyInfo.setProductCode("1");
        tBuyInfo.setUserId("lv");
        TBuyInfo buyInfo = tBuyInfoMapper.selectBuyInfoAndProductById_1(tBuyInfo);
        System.out.println(buyInfo);
    }

    /**
     * 级联测试2
     */
    @Test
    public void selectBuyInfoAndProductById_2() {
        TBuyInfo tBuyInfo=new TBuyInfo();
        tBuyInfo.setProductCode("1");
        tBuyInfo.setUserId("lv");
        TBuyInfo buyInfo = tBuyInfoMapper.selectBuyInfoAndProductById_2(tBuyInfo);
        System.out.println(buyInfo);
    }

    /**
     * 级联测试3
     */
    @Test
    public void selectBuyInfoAndProductById_3() {
        TBuyInfo tBuyInfo=new TBuyInfo();
        tBuyInfo.setProductCode("1");
        tBuyInfo.setUserId("lv");
        TBuyInfo buyInfo = tBuyInfoMapper.selectBuyInfoAndProductById_3(tBuyInfo);
        System.out.println(buyInfo);
    }

    /**
     * 级联测试4 分步懒加载
     */
    @Test
    public void selectBuyInfoAndProductByStep() {
        TBuyInfo tBuyInfo=new TBuyInfo();
        tBuyInfo.setProductCode("1");
        tBuyInfo.setUserId("lv");
        TBuyInfo buyInfo = tBuyInfoMapper.selectBuyInfoAndProductByStep(tBuyInfo);
        System.out.println(buyInfo.getUserId());
        System.out.println(buyInfo.gettProduct().getProductName());
    }

    /**
     * if和where标签测试
     */
    @Test
    public void selectByIfAndWhere() {
        TProduct tProduct=new TProduct();
//        tProduct.setProductCode(1);
        tProduct.setProductName("测试");
        List<TProduct> product = tProductMapper.selectByIfAndWhere(tProduct);
        System.out.println(product);
    }

    /**
     * update时if和set标签测试
     */
    @Test
    public void updateBySetAndIf() {
        TProduct tProduct=new TProduct();
        tProduct.setProductCode(1);
        tProduct.setProductName("测试11");
        tProduct.setCount(100);
        Integer cnt = tProductMapper.updateBySetAndIf(tProduct);
        System.out.println(cnt);
    }

    /**
     * select的foreach标签测试
     */
    @Test
    public void selectByForeach() {
        List<TProduct> product = tProductMapper.selectByForeach(Arrays.asList(1,2,3,4));
        System.out.println(product);
    }

    /**
     * 批量插入 foreach标签
     */
    @Test
    public void batchInsertByForeach() {
        List<TProduct> tProducts = new ArrayList<>();
        tProducts.add(new TProduct("test1",null,null,100));
        tProducts.add(new TProduct("test2",null,null,90));
        Integer cnt = tProductMapper.batchInsertByForeach(tProducts);
        System.out.println(cnt);
    }

    /**
     * 一级缓存
     * 需要开启事务，否则是2个sqlsessionFactory，无法使用一级缓存
     * 两个查询中间如果有更新，一级缓存会失效
     *
     */
    @Test
    @Transactional
    public void selectFirstCache() {
        TProduct tProduct = tProductMapper.selectById("1");
        System.out.println(tProduct);
        //更新数据，一级缓存失效
//        tProductMapper.insert(new TProduct());
        TProduct tProduct1 = tProductMapper.selectById("1");
        System.out.println(tProduct1);
        System.out.println(tProduct == tProduct1);
    }

    /**
     * 二级缓存
     * 二级缓存需要全局和maapper配置
     * select可以单独配置useCache
     *
     */
    @Test
    @Transactional
    public void selectSecondCache() {
        TProduct tProduct = tProductMapper.selectById("1");
        System.out.println(tProduct);
        //更新数据，一级缓存失效
        tProductMapper.insert(new TProduct());
        TProduct tProduct1 = tProductMapper.selectById("1");

        System.out.println(tProduct1);
        System.out.println(tProduct == tProduct1);
    }


    /**
     * 分页测试
     */
    @Test
    public void selectByPage(){
        Page<Object> page = PageHelper.startPage(8, 5);
        List<TProduct> tProducts = tProductMapper.selectReturnList();
        PageInfo<TProduct> tProductPageInfo = new PageInfo<>(tProducts);
        // 当前分页的数据条数
        System.out.println(tProductPageInfo.getSize());
        // 总页数
        System.out.println(tProductPageInfo.getPages());
        // 当前页码
        System.out.println(tProductPageInfo.getPageNum());
        // 每页显示多少条
        System.out.println(tProductPageInfo.getPageSize());
        for (TProduct tProduct : tProducts) {
            System.out.println(tProduct);
        }
    }

    @Autowired
    private SqlSession sqlSession;

    /**
     * 批量操作
     * 配置一个ExecutorType为Batch的SqlSession，通过这个sqlSession获取mapper，进行批量操作
     */
    @Test
    public void batchInsert(){
//        TProductCommonMapper tProductMapper = sqlSession.getMapper(TProductCommonMapper.class);
        for (int i = 0; i < 10; i++) {
            tProductMapper.insert(new TProduct(i+"",new Date(),new Date(),0));
        }
        sqlSession.commit();

    }
}
