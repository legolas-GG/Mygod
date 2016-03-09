package com.mygod.dao;

import com.mygod.entity.Collection;
import com.mygod.entity.Customer;
import com.mygod.entity.Merchandise;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by legolas on 2016/1/11.
 */
@Transactional
public interface CollectionDao extends CrudRepository<Collection , Integer> {
    public Collection findByCustomer_idAndMerchandise_id(Integer customer_id , Integer merchandise_id);
    public List<Collection> findByCustomer_id(Integer customer_id);
    public List<Collection> findByMerchandise_id(Integer merchandise_id);
}
