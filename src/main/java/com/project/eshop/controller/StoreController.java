package com.project.eshop.controller;

import com.project.eshop.dto.StoreDTO;
import com.project.eshop.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin(origins = "*")
public class StoreController {
	
	@Autowired
    private StoreService storeService;

    @GetMapping
    public List<StoreDTO> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Integer id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @PostMapping
    public ResponseEntity<StoreDTO> createStore(@Valid @RequestBody StoreDTO storeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.createStore(storeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Integer id, @Valid @RequestBody StoreDTO storeDTO) {
        return ResponseEntity.ok(storeService.updateStore(id, storeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Integer id) {
        if (storeService.deleteStore(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
