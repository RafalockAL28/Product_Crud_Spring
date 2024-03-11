package io.RafalockAL28.Products.controller;

import io.RafalockAL28.Products.dto.request.RequestProductDTO;
import io.RafalockAL28.Products.dto.response.ResponseProductDTO;
import io.RafalockAL28.Products.entity.Product;
import io.RafalockAL28.Products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseProductDTO> findById(@PathVariable Long id){//parametero sera passado na url
        return ResponseEntity.ok().body(productService.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }
    @PostMapping
    public ResponseEntity<ResponseProductDTO> create(@RequestBody RequestProductDTO productDTO, UriComponentsBuilder uriComponentsBuilder){

        ResponseProductDTO responseProductDTO = productService.create(productDTO);

        URI uri = uriComponentsBuilder.path(("/people/{id}")).buildAndExpand(responseProductDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(responseProductDTO);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseProductDTO> update(@RequestBody RequestProductDTO productDTO, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(productService.update(productDTO,id));
    }
    @DeleteMapping(value = "/{id}")
    private ResponseEntity<String> delete(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(productService.delete(id));
    }
}
