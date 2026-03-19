package com.project.eshop.service;

import com.project.eshop.dto.StoreInventoryDTO;
import com.project.eshop.entity.StoreInventory;
import com.project.eshop.repositories.ProductRepository;
import com.project.eshop.repositories.StoreInventoryRepository;
import com.project.eshop.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StoreInventoryService {
	
	@Autowired
    private StoreInventoryRepository storeInventoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<StoreInventoryDTO> getAllInventory() {
        return storeInventoryRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public StoreInventoryDTO getInventoryById(Integer id) {
        StoreInventory inventory = storeInventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory record not found!"));
        return convertToDTO(inventory);
    }

    public List<StoreInventoryDTO> getInventoryByStore(Integer storeId) {
        return storeInventoryRepository.findByStoreId(storeId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public StoreInventoryDTO createInventory(StoreInventoryDTO dto) {
        StoreInventory inventory = new StoreInventory();
        inventory.setQuantity(dto.getQuantity());

        inventory.setStore(storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store ID not found!")));
        inventory.setProduct(productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product ID not found!")));

        return convertToDTO(storeInventoryRepository.save(inventory));
    }

    public StoreInventoryDTO updateInventory(Integer id, StoreInventoryDTO dto) {
        StoreInventory existing = storeInventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory record not found!"));

        existing.setQuantity(dto.getQuantity());

        existing.setStore(storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store ID not found!")));
        existing.setProduct(productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product ID not found!")));

        return convertToDTO(storeInventoryRepository.save(existing));
    }

    public boolean deleteInventory(Integer id) {
        if (storeInventoryRepository.existsById(id)) {
            storeInventoryRepository.deleteById(id);
            return true;
        }
        return false;
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
