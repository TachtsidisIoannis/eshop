package com.project.eshop.controller;

import com.project.eshop.entity.StoreInventory;
import com.project.eshop.repositories.StoreInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class StoreInventoryController {
	
	@Autowired
    private StoreInventoryRepository storeInventoryRepository;

    @GetMapping
    public List<StoreInventory> getAllInventory() {
        return storeInventoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreInventory> getInventoryById(@PathVariable Integer id) {
        Optional<StoreInventory> inventory = storeInventoryRepository.findById(id);
        return inventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // BONUS: Check stock for a specific store (e.g. /api/inventory/store/2)
    @GetMapping("/store/{storeId}")
    public List<StoreInventory> getInventoryByStore(@PathVariable Integer storeId) {
        return storeInventoryRepository.findByStoreId(storeId);
    }

    @PostMapping
    public ResponseEntity<StoreInventory> createInventory(@RequestBody StoreInventory inventory) {
        StoreInventory savedInventory = storeInventoryRepository.save(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreInventory> updateInventory(@PathVariable Integer id, @RequestBody StoreInventory inventoryDetails) {
        Optional<StoreInventory> optionalInventory = storeInventoryRepository.findById(id);

        if (optionalInventory.isPresent()) {
            StoreInventory existingInventory = optionalInventory.get();
            existingInventory.setQuantity(inventoryDetails.getQuantity());
            existingInventory.setStore(inventoryDetails.getStore());
            existingInventory.setProduct(inventoryDetails.getProduct());
            
            StoreInventory updatedInventory = storeInventoryRepository.save(existingInventory);
            return ResponseEntity.ok(updatedInventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
        if (storeInventoryRepository.existsById(id)) {
            storeInventoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
