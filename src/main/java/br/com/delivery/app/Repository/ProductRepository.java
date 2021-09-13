package br.com.delivery.app.Repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.delivery.app.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @Modifying
  @Transactional
  @Query("update Product p set p.name = :name ,p.price = :price ,p.promotion_price = :promotion_price ,p.description = :description ,p.category_id = :category_id ,p.photo = :photo where p.id = :id")
  void update(@Param(value = "id") long id , 
		      @Param(value = "name") String  name ,
		      @Param(value = "price") BigDecimal  price, 
		      @Param(value = "promotion_price") BigDecimal  promotion_price, 
		      @Param(value = "description") String  description,
		      @Param(value = "category_id") long  category_id,
		      @Param(value = "photo") String  photo);

}
