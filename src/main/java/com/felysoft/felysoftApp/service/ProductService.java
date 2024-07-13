package com.felysoft.felysoftApp.service;

import com.felysoft.felysoftApp.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    List<Product> findAll() throws Exception;

    Product findById(Long id);

    @Transactional
    void create(Product product);

    @Transactional
    @Modifying
    void update(Product product);

    @Transactional
    @Modifying
    void delete(Product product);
}
