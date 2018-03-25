/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.arquillian.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.inject.Singleton;

/**
 *
 * @author SEPALMM
 */
@Singleton
@Lock(LockType.READ)
public class SingletonOrderRepository implements OrderRepository {

    private List<List<String>> orders;

    @Override
    @Lock(LockType.WRITE)
    public void addOrder(List<String> order) {
        orders.add(order);
    }

    @Override
    public List<List<String>> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    @Override
    public int getOrderCount() {
        return orders.size();
    }

    @PostConstruct
    void initialize() {
        orders = new ArrayList<List<String>>();
    }
}
