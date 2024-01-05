package com.merrach.inventoryservice.service;

import com.merrach.inventoryservice.dto.InventoryResponse;
import com.merrach.inventoryservice.repository.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
   private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
   public List<InventoryResponse> isInStock(List<String> skuCode){
     return  inventoryRepository.findBySkuCodeIn(skuCode)
               .stream().map(inventory->{
                   return  InventoryResponse.builder()
                           .skuCode(inventory.getSkuCode())
                           .isInStock(inventory.getQuantity()>0)
                           .build();
               }).toList();
   }

}
