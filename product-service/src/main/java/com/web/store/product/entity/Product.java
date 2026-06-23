package com.web.store.product.entity;

import java.util.List;
import java.util.Set;

import org.hibernate.engine.internal.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumeratedValue;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(nullable = false)
private String name;

@Column(nullable = false)
private String description;

@Column(nullable = false)
private String imageUrl;

private boolean bestSelling;

private boolean newArrival;

@ElementCollection(fetch = FetchType.EAGER)
@Enumerated(EnumType.STRING)
@CollectionTable(
		name="product_categories",
		joinColumns = @JoinColumn(name="product_id")		
		)
private Set<Category>categories;

@OneToMany(
		mappedBy = "product",
		 cascade = CascadeType.ALL,
		 orphanRemoval = true
		)
private List<ProductSize>sizes;


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

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getImageUrl() {
	return imageUrl;
}

public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

public boolean isBestSelling() {
	return bestSelling;
}

public void setBestSelling(boolean bestSelling) {
	this.bestSelling = bestSelling;
}

public boolean isNewArrival() {
	return newArrival;
}

public void setNewArrival(boolean newArrival) {
	this.newArrival = newArrival;
}

public Set<Category> getCategories() {
	return categories;
}

public void setCategories(Set<Category> categories) {
	this.categories = categories;
}

public List<ProductSize> getSizes() {
	return sizes;
}

public void setSizes(List<ProductSize> sizes) {
	this.sizes = sizes;
}


}
