package br.com.delivery.app.Model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "products")
public class Product implements Serializable {


private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message="Por favor entre com o nome")
private String name;

@NotNull(message="Por favor entre com o preço")
private BigDecimal price;

private BigDecimal promotion_price;

private String description;

private long category_id;

@Column(nullable = true, length = 64)
private String photo;


public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public BigDecimal getPrice() {
	return price;
}

public void setPrice(BigDecimal price) {
	this.price = price;
}

public BigDecimal getPromotion_price() {
	return promotion_price;
}

public void setPromotion_price(BigDecimal promotion_price) {
	this.promotion_price = promotion_price;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public long getCategory_id() {
	return category_id;
}

public void setCategory_id(long category_id) {
	this.category_id = category_id;
}

public String getPhoto() {
	return photo;
}

public void setPhoto(String photo) {
	this.photo = photo;
}


}
