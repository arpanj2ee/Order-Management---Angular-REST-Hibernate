package com.order.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.order.model.Order;
import com.order.util.HibernateUtil;

/**
 * @author arpan
 *
 */
public class OrderRepository {
	
	public List<Order> getOrders(){
		Session session = HibernateUtil.getSession();    
		Query query = session.createQuery("from Order");
		List<Order> list = query.list();
		return list;
	}
	
	public List<Order> getByOrderId(Integer id){
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Order where orderId=:id");
		query.setParameter("id", id);
		List<Order> list = query.list();
		return list;
	}
	public void createOrder(Order order){
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		Order createOrder = new Order();
		createOrder.setOrderName(order.getOrderName());
		createOrder.setOrderPrice(order.getOrderPrice());
		createOrder.setOrderDescription(order.getOrderDescription());
		session.save(createOrder);		
		transaction.commit();
		session.close();
	}
	
	public Order updateOrder(Order order,Integer id){
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		Order retrieveOrder = (Order) session.load(Order.class, id);
		retrieveOrder.setOrderName(order.getOrderName());
		retrieveOrder.setOrderPrice(order.getOrderPrice());
		retrieveOrder.setOrderDescription(order.getOrderDescription());
		session.update(retrieveOrder);		
		transaction.commit();
		session.close();
		return retrieveOrder;
	}
	
	public int deleteOrder(Integer id){
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		String hql = "DELETE FROM Order where orderId=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		int rowCount = query.executeUpdate();
		transaction.commit();
		session.close();
		return rowCount;
	}
}