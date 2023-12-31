package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    HashMap<String,Order> orderHashMap=new HashMap<>();
    HashMap<String,DeliveryPartner> deliveryPartnerHashMap=new HashMap<>();

    HashMap<DeliveryPartner, List<Order>> deliveryPartnerOrderHashMap=new HashMap<>();
    public void addOrder(Order order){

        orderHashMap.put(order.getId(), order);

    }
    public void addPartner(String partnerId){
        DeliveryPartner temp =new DeliveryPartner(partnerId);
        deliveryPartnerHashMap.put(partnerId, temp);

    }

    public void addOrderPartnerPair(String orderId,String partnerId){

        if(deliveryPartnerOrderHashMap.containsKey(deliveryPartnerHashMap.get(partnerId))){
            deliveryPartnerHashMap.get(partnerId).setNumberOfOrders(deliveryPartnerHashMap.get(partnerId).getNumberOfOrders()+1);
            deliveryPartnerOrderHashMap.get(deliveryPartnerHashMap.get(partnerId)).add(orderHashMap.get(orderId));
        }
        else {
            List<Order> temp=new ArrayList<>();
            temp.add(orderHashMap.get(orderId));
            deliveryPartnerHashMap.get(partnerId).setNumberOfOrders(1);
             deliveryPartnerOrderHashMap.put(deliveryPartnerHashMap.get(partnerId),temp);
        }


    }
    public Order getOrderById(String orderId){

        return orderHashMap.get(orderId);


    }

    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnerHashMap.get(partnerId);
    }
    public List<Order> getOrderCountByPartnerId(String partnerId){

            return deliveryPartnerOrderHashMap.get(deliveryPartnerHashMap.get(partnerId));

    }

    public List<Order> getOrdersByPartnerId(String partnerId) {

        return deliveryPartnerOrderHashMap.get(deliveryPartnerHashMap.get(partnerId));
    }

    public HashMap<String, Order> getAllOrders() {

        return orderHashMap;
    }

    public HashMap<DeliveryPartner, List<Order>> getAssignedOrder() {
        return deliveryPartnerOrderHashMap;
    }

    public List<Order> getOrdersLeftAfterGivenTimeByPartnerId(String partnerId) {
        return deliveryPartnerOrderHashMap.get(deliveryPartnerHashMap.get(partnerId));
    }

    public List<Order> getLastDeliveryTimeByPartnerId(String partnerId) {
        return deliveryPartnerOrderHashMap.get(deliveryPartnerHashMap.get(partnerId));
    }

    public void deletePartnerById(String partnerId) {
        deliveryPartnerOrderHashMap.remove(deliveryPartnerHashMap.get(partnerId));
        deliveryPartnerHashMap.remove(partnerId);
    }

    public void deleteOrderById(String orderId) {

    Order temp=orderHashMap.get(orderId);


    for(DeliveryPartner i:deliveryPartnerOrderHashMap.keySet()){

        List<Order> ll=deliveryPartnerOrderHashMap.get(i);
        if(ll.contains(temp)){
            i.setNumberOfOrders(i.getNumberOfOrders()-1);
            ll.remove(temp);
        }

    }
        orderHashMap.remove(orderId);


    }
}
