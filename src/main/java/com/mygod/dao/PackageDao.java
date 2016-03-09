package com.mygod.dao;

import com.mygod.entity.Package;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by legolas on 2016/1/11.
 */
@Transactional
public  interface PackageDao extends CrudRepository<Package, Integer>{
    public List<Package> findByMerchandise_id(Integer merchandise_id);
}
