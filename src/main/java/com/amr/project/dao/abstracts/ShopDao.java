package com.amr.project.dao.abstracts;

import com.amr.project.model.dto.PaginationDto;
import com.amr.project.model.entity.Shop;

import java.util.List;

public interface ShopDao extends ReadWriteDao<Shop, Long> {

    List<Shop> findAllShopForUser();
    List<Shop> findPopularShop();
    List<Shop> findShopsBySearchRequest(String query);
    PaginationDto findAllShop(int page, int size, int offset);
}
