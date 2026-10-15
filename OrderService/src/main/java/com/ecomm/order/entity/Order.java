package com.ecomm.order.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	@Column(unique=true)
	private String orderNumber;
	
	@Enumerated(EnumType.STRING)
	private Integer userId;
	
	private OrderStatus orderstatus=OrderStatus.pending;
	
	private Double subTotal;
	
	private Double totalAmount;
	
	private String currency;
	
	@Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	private LocalDateTime modifiedAt;
	
	@PreUpdate
	public void setModifiedAt() {
		this.modifiedAt = LocalDateTime.now();
	}
	
	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL , orphanRemoval = true)
	private List<OrderItem> orderItems;
	
	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL , orphanRemoval = true)
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL , orphanRemoval = true)
	private List<OrderStatusHistory> statusHistories;

}
