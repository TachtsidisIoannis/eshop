package com.project.eshop.service;

import com.project.eshop.dto.StoreDTO;
import com.project.eshop.entity.Store;
import com.project.eshop.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {
	
	@Autowired
    private StoreRepository storeRepository;

    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public StoreDTO getStoreById(Integer id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(this::convertToDTO).orElse(null);
    }

    private StoreDTO convertToDTO(Store store) {
        StoreDTO dto = new StoreDTO();
        dto.setId(store.getId());
        dto.setCity(store.getCity());
        dto.setAddress(store.getAddress());
        return dto;
    }
}
