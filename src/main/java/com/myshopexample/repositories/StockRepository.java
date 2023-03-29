package com.myshopexample.repositories;

import com.myshopexample.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends JpaRepository<Stock,Long> {
public Stock findFirstByEnableAndProduct_IdOrderByCreateDateAsc(Boolean enable, Long id);
@Modifying
@Query("UPDATE Stock s SET s.quantity = s.quantity - :quantity WHERE s.quantity - :quantity >= 0 AND s.id = :id")
    public int updateStock(@Param("quantity") int quantity, @Param("id") Long id);
@Query("SELECT s.price FROM Stock s WHERE s.id = :id AND s.enable = :enable ORDER BY s.createDate")
    public Double findPriceByProduct_IdAndEnable(@Param("id") Long id,@Param("enable") Boolean enable);
}
