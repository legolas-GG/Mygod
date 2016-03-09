package com.mygod.dao;

import com.mygod.entity.Merchandise;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by legolas on 2016/1/10.
 */
@Transactional
public interface MerchandiseDao extends CrudRepository<Merchandise,Integer> {
    public List<Merchandise> findByTypename(String typename);
}
