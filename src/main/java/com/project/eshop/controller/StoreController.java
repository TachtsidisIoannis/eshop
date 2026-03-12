package com.project.eshop.controller;

import com.project.eshop.entity.Store;
import com.project.eshop.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin(origins = "*")
public class StoreController {
	
	@Autowired
    private StoreRepository storeRepository;

    @GetMapping
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Integer id) {
        Optional<Store> store = storeRepository.findById(id);
        return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        Store savedStore = storeRepository.save(store);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStore);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Integer id, @RequestBody Store storeDetails) {
        Optional<Store> optionalStore = storeRepository.findById(id);

        if (optionalStore.isPresent()) {
            Store existingStore = optionalStore.get();
            existingStore.setCity(storeDetails.getCity());
            existingStore.setAddress(storeDetails.getAddress());
            
            Store updatedStore = storeRepository.save(existingStore);
            return ResponseEntity.ok(updatedStore);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Integer id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
