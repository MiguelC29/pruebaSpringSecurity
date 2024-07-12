package com.felysoft.felysoftApp.repository;

import com.felysoft.felysoftApp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /*
    @PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
    Product save(Product product);*/
}
