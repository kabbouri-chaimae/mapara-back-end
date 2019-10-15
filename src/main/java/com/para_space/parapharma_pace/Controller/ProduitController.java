package com.para_space.parapharma_pace.Controller;


import com.para_space.parapharma_pace.dao.ProduitRepository;
import com.para_space.parapharma_pace.entities.Produit;
import com.para_space.parapharma_pace.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ProduitController {


   @Autowired
    private ProduitRepository ProduitRepository;

    @GetMapping("/Produits")
    public List<Produit> getAllProduits() {
        return ProduitRepository.findAll();
    }

    @GetMapping("/Produits/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable(value = "id") Long ProduitId)
            throws ResourceNotFoundException {
        Produit Produit= ProduitRepository.findById(ProduitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not found for this id :: " + ProduitId));
        return ResponseEntity.ok().body(Produit);
    }

    @PostMapping("/Produits")
    public Produit createProduit(@Valid @RequestBody Produit Produit) {
        return ProduitRepository.save(Produit);
    }

    @PutMapping("/Produits/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable(value = "id") Long ProduitId,
                                                   @Valid @RequestBody Produit ProduitDetails) throws ResourceNotFoundException {
        Produit Produit = ProduitRepository.findById(ProduitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not found for this id :: " + ProduitId));

        Produit.setTitre(ProduitDetails.getTitre());
        Produit.setPrix(ProduitDetails.getPrix());
        Produit.setCode_barres(ProduitDetails.getCode_barres());
        Produit.setQuantitelivre(ProduitDetails.getQuantitelivre());
        Produit.setQuantite(ProduitDetails.getQuantite());

        final Produit updatedProduit = ProduitRepository.save(Produit);
        return ResponseEntity.ok(updatedProduit);
    }

    @DeleteMapping("/Produits/{id}")
    public Map<String, Boolean> deleteProduit(@PathVariable(value = "id") Long ProduitId)
            throws ResourceNotFoundException {
        Produit Produit = ProduitRepository.findById(ProduitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not found for this id :: " + ProduitId));

        ProduitRepository.delete(Produit);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}