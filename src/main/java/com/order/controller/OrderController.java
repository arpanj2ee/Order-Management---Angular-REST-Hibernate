package com.order.controller;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.order.model.Order;
import com.order.repository.OrderRepository;


/**
 * @author arpan
 *
 */
@Path("orders")
public class OrderController {
	
	private OrderRepository orderRepository = new OrderRepository();
	
	@GET
	@Produces("application/json")
	public List<Order> getAllOrders(){
		List<Order> list =  orderRepository.getOrders();
		return list;
	}
	
	@Path("/{orderId}")
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	public List<Order> getOrderById(@PathParam("orderId") Integer orderId){
		List<Order> list = orderRepository.getByOrderId(orderId);
		return list;		
	}
	
	@POST
	@Consumes("application/json")
	public Response addOrder(Order order){
		orderRepository.createOrder(order);
		return Response.ok(order).build();
	} 
	
	@Path("/{orderId}")
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response updateOrder(Order order,@PathParam("orderId") Integer orderId){
		Order updatedOrder = orderRepository.updateOrder(order, orderId);
		return Response.ok(updatedOrder).build();
	}
	
	@Path("/{orderId}")
	@DELETE
	@Consumes("application/json")
	public Response deleteOrder(@PathParam("orderId") Integer orderId){
		int rowCount = orderRepository.deleteOrder(orderId);
		if(rowCount == 0)
			return Response.status(Response.Status.BAD_REQUEST).build();
		return Response.ok().build();
	}
}
