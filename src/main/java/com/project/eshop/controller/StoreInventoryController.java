package com.project.eshop.controller;

import com.project.eshop.dto.StoreInventoryDTO;
import com.project.eshop.service.StoreInventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class StoreInventoryController {
	
	@Autowired
    private StoreInventoryService storeInventoryService;

    @GetMapping
    public List<StoreInventoryDTO> getAllInventory() {
        return storeInventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreInventoryDTO> getInventoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(storeInventoryService.getInventoryById(id));
    }

    @GetMapping("/store/{storeId}")
    public List<StoreInventoryDTO> getInventoryByStore(@PathVariable Integer storeId) {
        return storeInventoryService.getInventoryByStore(storeId);
    }

    @PostMapping
    public ResponseEntity<StoreInventoryDTO> createInventory(@Valid @RequestBody StoreInventoryDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storeInventoryService.createInventory(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreInventoryDTO> updateInventory(@PathVariable Integer id, @Valid @RequestBody StoreInventoryDTO dto) {
        return ResponseEntity.ok(storeInventoryService.updateInventory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
        if (storeInventoryService.deleteInventory(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
