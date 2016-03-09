package com.mygod.dao;

import com.mygod.entity.Address;
import com.mygod.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by legolas on 2016/1/9.
 */
@Transactional
    public interface AddressDao extends CrudRepository<Address , Integer> {
        public List<Address> findByCustomer_id(Integer customer_id);
    }

