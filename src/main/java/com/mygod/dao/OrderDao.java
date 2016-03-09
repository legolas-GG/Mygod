package com.mygod.dao;

import com.mygod.entity.Order;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by legolas on 2016/1/11.
 */
@Transactional
public interface OrderDao extends CrudRepository<Order, Integer>{
    public List<Order> findByCustomer_id(Integer customer);
}
