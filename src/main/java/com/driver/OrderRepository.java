package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class OrderRepository {
    HashMap<String,Order> orderHashMap=new HashMap<>();
    HashMap<String,DeliveryPartner> deliveryPartnerHashMap=new HashMap<>();

    HashMap<DeliveryPartner,Order> deliveryPartnerOrderHashMap=new HashMap<>();
    public void addOrder(Order order){

        orderHashMap.put(order.getId(), order);

    }
    public void addPartner(String partnerId){
        DeliveryPartner temp =new DeliveryPartner(partnerId);
        deliveryPartnerHashMap.put(partnerId, temp);

    }

    public void addOrderPartnerPair(String orderId,String partnerId){

        deliveryPartnerOrderHashMap.put(deliveryPartnerHashMap.get(partnerId),orderHashMap.get(orderId));
       int temp= deliveryPartnerHashMap.get(partnerId).getNumberOfOrders();
       deliveryPartnerHashMap.get(partnerId).setNumberOfOrders(temp+1);



    }
    public Order getOrderById(String orderId){

        return orderHashMap.get(orderId);


    }

    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnerHashMap.get(partnerId);
    }
    public Integer getOrderCountByPartnerId(String partnerId){

        return deliveryPartnerHashMap.get(partnerId).getNumberOfOrders();

    }

}
