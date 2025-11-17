package com.example.store.respositories;

import com.example.store.dtos.ProductSummary;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
  /*List<Product> findByName(String name);
  List<Product> findByNameLikeIgnoreCaseOOrderByNameAsc(String name);*/
  // Limit (Top/First)
  //List<Product> findfirst5BynameLinkeOrderByPrice(String name);

  //JPQL-SQL
  //Find products whose prices are in a given range and sort by name
  //List<Product> findByPriceBetweenOrOrderByName(BigDecimal min, BigDecimal max);
  /*@Query(value = "select * from products p where p.price between :min and :max order by p.name", nativeQuery = true)
  List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
*/
  //JPQL
  /*@Query("select p from Product p where p.price between :min and :max order by p.name")
  List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);*/

 /* @Query("select p from Product p join p.category where p.price between :min and :max order by p.name")
  List<Product> findProductsAutoJPQL(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
*/
  @Query("select count(*) from Product p where p.price between :min and :max")
  long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

  @Modifying
  @Query("update Product  p set p.price=:newPrice where p.category.id= :categoryId")
  void updatePriceByCategory(BigDecimal newPrice,Byte categoryId);

  //List<Product> findByCategory(Category category);
  //List<ProductSummary> findByCategory(Category category);

 /* @Query("select new com.example.store.dtos.ProductSummaryDTO(p.id,p.name)  from Product p where p.category = :category")
  List<ProductSummary> findByCategory(@Param("category") Category category);*/

    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);
}
