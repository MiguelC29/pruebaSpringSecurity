package com.felysoft.felysoftApp.service.imp;

import com.felysoft.felysoftApp.entity.Product;
import com.felysoft.felysoftApp.repository.ProductRepository;
import com.felysoft.felysoftApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() throws Exception {
        return this.productRepository.findProductsByEliminatedFalse();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findProductByIdProductAndEliminatedFalse(id);
    }

    @Override
    public void create(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        this.productRepository.save(product);
    }
}
