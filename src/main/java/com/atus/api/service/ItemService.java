package com.atus.api.service;

import com.atus.api.entity.ItemEntity;
import com.atus.api.model.Item;

import java.util.List;

public interface ItemService {
    ItemEntity toEntity(Item m);
    List<ItemEntity> toEntityList(List<Item> items);
    Item toModel(ItemEntity e);
    List<Item> toModelList(List<ItemEntity> items);
}
