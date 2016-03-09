package com.mygod.model;

import java.util.List;

/**
 * Created by legolas on 2016/1/13.
 */
public class PackageReq {
    private List<MerchandiseAndPackageReq> mapReqList;

    public List<MerchandiseAndPackageReq> getMapReqList() {
        return mapReqList;
    }

    public void setMapReqList(List<MerchandiseAndPackageReq> mapReqList) {
        this.mapReqList = mapReqList;
    }
}
