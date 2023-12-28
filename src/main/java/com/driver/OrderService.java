package com.driver;

import org.springframework.stereotype.Service;

@Service

public class OrderService {

    OrderRepository repoObj=new OrderRepository();

    public void addOrder(Order order){
        repoObj.addOrder(order);


    }
    public void addPartner(String partnerId){
        repoObj.addPartner(partnerId);
    }
    public void addOrderPartnerPair(String orderId,String partnerId){
        repoObj.addOrderPartnerPair(orderId,partnerId);
    }
    public Order getOrderById(String orderId){
        Order temp=repoObj.getOrderById(orderId);
        return temp;
    }
    public DeliveryPartner getPartnerById(String partnerId){
        DeliveryPartner temp= repoObj.getPartnerById(partnerId);
        return temp;
    }
    public Integer getOrderCountByPartnerId(String partnerId){
        Integer temp=repoObj.getOrderCountByPartnerId(partnerId);

        return temp;

    }

}
