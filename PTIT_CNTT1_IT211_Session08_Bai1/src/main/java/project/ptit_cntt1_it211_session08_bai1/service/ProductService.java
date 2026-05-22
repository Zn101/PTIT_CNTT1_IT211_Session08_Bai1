package project.ptit_cntt1_it211_session08_bai1.service;

import project.ptit_cntt1_it211_session08_bai1.entity.Product;

public interface ProductService {

    void stockIn(
            String sku,
            Integer quantity,
            String username,
            String role
    );

    void stockOut(
            String sku,
            Integer quantity,
            String username,
            String role
    );

    void deleteProduct(
            Long id,
            String username,
            String role
    );

    String inspectInventory(
            String username,
            String role
    );
}
