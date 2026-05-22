package project.ptit_cntt1_it211_session08_bai1.repository;

import project.ptit_cntt1_it211_session08_bai1.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    @Modifying
    @Query("""
            UPDATE Product p
            SET p.quantity = p.quantity + :quantity
            WHERE p.sku = :sku
            """)
    int stockIn(
            @Param("sku") String sku,
            @Param("quantity") Integer quantity
    );

    @Modifying
    @Query("""
            UPDATE Product p
            SET p.quantity = p.quantity - :quantity
            WHERE p.sku = :sku
            AND p.quantity >= :quantity
            """)
    int stockOut(
            @Param("sku") String sku,
            @Param("quantity") Integer quantity
    );
}
