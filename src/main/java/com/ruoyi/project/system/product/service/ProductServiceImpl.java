package com.ruoyi.project.system.product.service;

import com.ruoyi.project.system.product.dao.IProductDao;
import com.ruoyi.project.system.product.domain.Product;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;


/**
 * 产品 业务层处理
 *
 * @author ruoyi
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    private static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

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
        int count = 0;
        count = productDao.deleteProductById(productId);
        return count;
    }

    @Override
    public int batchDeleteProduct(Long[] ids) {
        return 0;
    }


    @Override
    public int saveProduct(Product product) {
        int count = 0;
        count = productDao.insertProduct(product);
        return count;
    }
}
