package io.RafalockAL28.Products.service;

import io.RafalockAL28.Products.dto.request.RequestProductDTO;
import io.RafalockAL28.Products.dto.response.ResponseProductDTO;
import io.RafalockAL28.Products.entity.Product;
import io.RafalockAL28.Products.repository.ProductRepository;
import io.RafalockAL28.Products.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ProductServiceImplements implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public ResponseProductDTO findById(Long id) {
        return productMapper.toProductDTO(returnProduct(id));
    }

    @Override
    public List<ResponseProductDTO> findAll() {
        return  productMapper.toProductDTO(productRepository.findAll());


    }


    //GET
    @Override
    public ResponseProductDTO create(RequestProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ResponseProductDTO update(RequestProductDTO productDTO, Long id) {

        //Recuperando o usuario pelo id
        Product product = returnProduct(id);

        //Atualizando os dados
        productMapper.updateProductData(product,productDTO);

        //Salvando
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public String delete(Long id) {

        productRepository.deleteById(id);

        return ("Product deleted");
    }


    private Product returnProduct(Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    }
}
