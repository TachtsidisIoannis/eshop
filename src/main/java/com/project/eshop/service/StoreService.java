package com.project.eshop.service;

import com.project.eshop.dto.StoreDTO;
import com.project.eshop.entity.Store;
import com.project.eshop.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {
	
	@Autowired
    private StoreRepository storeRepository;

    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public StoreDTO getStoreById(Integer id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found!"));
        return convertToDTO(store);
    }

    public StoreDTO createStore(StoreDTO storeDTO) {
        Store store = new Store();
        store.setCity(storeDTO.getCity());
        store.setAddress(storeDTO.getAddress());
        return convertToDTO(storeRepository.save(store));
    }

    public StoreDTO updateStore(Integer id, StoreDTO storeDTO) {
        Store existingStore = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found!"));
        
        existingStore.setCity(storeDTO.getCity());
        existingStore.setAddress(storeDTO.getAddress());
        return convertToDTO(storeRepository.save(existingStore));
    }

    public boolean deleteStore(Integer id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private StoreDTO convertToDTO(Store store) {
        StoreDTO dto = new StoreDTO();
        dto.setId(store.getId());
        dto.setCity(store.getCity());
        dto.setAddress(store.getAddress());
        return dto;
    }
}
