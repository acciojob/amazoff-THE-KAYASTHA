package com.driver;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


        List<Order> temp=repoObj.getOrderCountByPartnerId(partnerId);
        Integer ans= temp.size();
        return ans;

    }

    public List<String> getOrdersByPartnerId(String partnerId) {

        List<Order> temp=repoObj.getOrdersByPartnerId(partnerId);
        List<String> ans=new ArrayList<>();

        for(Order i:temp){
            ans.add(i.getId());
        }
        return ans;
    }

    public List<String> getAllOrders() {

        HashMap<String,Order> temp= repoObj.getAllOrders();
        List<String> ans=new ArrayList<>();
        for(String i:temp.keySet()){
            ans.add(i);
        }
        return ans;

    }

    public Integer getCountOfUnassignedOrders() {

        HashMap<String,Order> totalOrder=repoObj.getAllOrders();
        HashMap<DeliveryPartner, List<Order>> assignedOrder =repoObj.getAssignedOrder();
        Integer s1=0;
        Integer s2=0;

        s1=totalOrder.size();

        for(DeliveryPartner i:assignedOrder.keySet()){
            s2+=assignedOrder.get(i).size();
        }

        return s1-s2;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String givenTime, String partnerId) {
        List<Order> partnerOrders=repoObj.getOrdersLeftAfterGivenTimeByPartnerId(partnerId);
        String[] timeComponents = givenTime.split(":");
        int HH = Integer.parseInt(timeComponents[0]);
        int MM = Integer.parseInt(timeComponents[1]);
        Integer count=0;
        int currentTime=HH*60+MM;
        if (partnerOrders != null) {
            for (Order order : partnerOrders) {
                if (order.getDeliveryTime()  > currentTime) {
                    count++;
                }
            }
        }



        return count;

    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        List<Order> partnerOrders=repoObj.getLastDeliveryTimeByPartnerId(partnerId);
        int lastDeliveryTime=0;
        if (partnerOrders != null && !partnerOrders.isEmpty()) {
            lastDeliveryTime = partnerOrders.get(partnerOrders.size() - 1).getDeliveryTime();
        }


        String ans="";

        int HH=lastDeliveryTime/60;
        if(HH<10){
            ans+="0"+HH;
        }
        else{
            ans+=HH;
        }

        ans+=":";
        int MM=lastDeliveryTime%60;
            if(MM<10){
                ans+="0"+MM;
            }
            else{
                ans+=MM;
            }
        return ans;

    }

    public void deletePartnerById(String partnerId) {

        repoObj.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId) {
        repoObj.deleteOrderById(orderId);

    }
}
