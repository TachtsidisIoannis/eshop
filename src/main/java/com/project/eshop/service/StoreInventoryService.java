package com.project.eshop.service;

import com.project.eshop.dto.StoreInventoryDTO;
import com.project.eshop.entity.StoreInventory;
import com.project.eshop.repositories.StoreInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreInventoryService {
	
	@Autowired
    private StoreInventoryRepository storeInventoryRepository;

    public List<StoreInventoryDTO> getAllInventory() {
        return storeInventoryRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public StoreInventoryDTO getInventoryById(Integer id) {
        Optional<StoreInventory> inventory = storeInventoryRepository.findById(id);
        return inventory.map(this::convertToDTO).orElse(null);
    }

    public List<StoreInventoryDTO> getInventoryByStore(Integer storeId) {
        return storeInventoryRepository.findByStoreId(storeId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private StoreInventoryDTO convertToDTO(StoreInventory inventory) {
        StoreInventoryDTO dto = new StoreInventoryDTO();
        dto.setId(inventory.getId());
        dto.setQuantity(inventory.getQuantity());

        if (inventory.getStore() != null) {
            dto.setStoreId(inventory.getStore().getId());
            dto.setStoreCity(inventory.getStore().getCity());
        }

        if (inventory.getProduct() != null) {
            dto.setProductId(inventory.getProduct().getId());
            dto.setProductName(inventory.getProduct().getName());
        }

        return dto;
    }
}
