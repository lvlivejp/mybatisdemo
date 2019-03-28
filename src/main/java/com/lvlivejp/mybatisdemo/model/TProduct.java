package com.lvlivejp.mybatisdemo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "`t_product`")
public class TProduct implements Serializable {

    public TProduct(String productName, Date startDate, Date endDate, Integer count) {
        this.productName = productName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.count = count;
    }

    public TProduct() {
    }

    /**
     * 商品编码
     * @GeneratedValue(strategy= GenerationType.IDENTITY)会执行查主键的sql：SELECT LAST_INSERT_ID()
     * @GeneratedValue(generator = "JDBC") 直接返回主键到bean，不会再执行查询sql
     */
    @Id
    @Column(name = "product_code")
    @GeneratedValue(generator = "JDBC",strategy= GenerationType.IDENTITY)
    private Integer productCode;

    /**
     * 商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 库存
     */
    private Integer count;

    /**
     * 获取商品编码
     *
     * @return product_code - 商品编码
     */
    public Integer getProductCode() {
        return productCode;
    }

    /**
     * 设置商品编码
     *
     * @param productCode 商品编码
     */
    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取商品名称
     *
     * @return product_name - 商品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置商品名称
     *
     * @param productName 商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取开始时间
     *
     * @return start_date - 开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置开始时间
     *
     * @param startDate 开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取结束时间
     *
     * @return end_date - 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束时间
     *
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取库存
     *
     * @return count - 库存
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置库存
     *
     * @param count 库存
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TProduct{" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", count=" + count +
                '}';
    }
}
