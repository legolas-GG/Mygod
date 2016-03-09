package com.mygod.dao;

import com.mygod.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by siren93 on 16/1/6.
 */
@Transactional
public interface CustomerDao extends CrudRepository<Customer, Integer> {
    public Customer findByName(String name);
    public Customer findByPhone(String phone);
}
