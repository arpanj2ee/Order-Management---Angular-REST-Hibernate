package com.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author arpan
 *
 */
@Entity
@Table(name="shop_online")
public class Order {
		
		@Id
		@Column(name="od_id")
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer orderId;
		
		@Column(name="od_name")
		private String orderName;
		
		@Column(name="od_price")
		private Integer orderPrice;
		
		@Column(name="od_description")
		private String orderDescription;

		public Order() {
			
		}
	
		public Integer getOrderId() {
			return orderId;
		}

		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}

		public String getOrderName() {
			return orderName;
		}

		public void setOrderName(String orderName) {
			this.orderName = orderName;
		}

		public Integer getOrderPrice() {
			return orderPrice;
		}

		public void setOrderPrice(Integer orderPrice) {
			this.orderPrice = orderPrice;
		}

		public String getOrderDescription() {
			return orderDescription;
		}

		public void setOrderDescription(String orderDescription) {
			this.orderDescription = orderDescription;
		}
		
		
	}


