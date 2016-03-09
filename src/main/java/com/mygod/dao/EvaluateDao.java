package com.mygod.dao;

import com.mygod.entity.Evaluate;
import com.mygod.entity.Merchandise;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by legolas on 2016/1/10.
 */
@Transactional
public interface EvaluateDao extends CrudRepository<Evaluate, Integer>{
    public List<Evaluate> findByMerchandise_id(Integer merchandise_id);
}
