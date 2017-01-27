package com.aurora.product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by atif on 1/27/17.
 */
public interface ProductRepository extends JpaRepository<Product, Long>
{
}
