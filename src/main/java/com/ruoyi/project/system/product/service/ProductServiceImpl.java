package com.ruoyi.project.system.product.service;

import com.ruoyi.project.system.product.dao.IProductDao;
import com.ruoyi.project.system.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品 业务层处理
 * 
 * @author ruoyi
 */
@Service("productService")
public class ProductServiceImpl implements IProductService
{
    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> selectProductAll() {
        return productDao.selectProductAll();
    }

    @Override
    public Product selectProductById(Long productId) {
        return productDao.selectProductById(productId);
    }

    @Override
    public int deleteProductById(Long productId) {
        return 0;
    }

    @Override
    public int batchDeleteProduct(Long[] ids) {
        return 0;
    }

    @Override
    public int saveProduct(Product product) {
        return 0;
    }
}
