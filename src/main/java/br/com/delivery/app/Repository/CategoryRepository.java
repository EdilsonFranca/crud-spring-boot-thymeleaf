package br.com.delivery.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.delivery.app.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
