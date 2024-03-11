package io.RafalockAL28.Products.util;

import io.RafalockAL28.Products.dto.request.RequestProductDTO;
import io.RafalockAL28.Products.dto.response.ResponseProductDTO;
import io.RafalockAL28.Products.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {


    //Request
    public Product toProduct(RequestProductDTO productDTO){
        return  Product.builder()
                .name(productDTO.getName())
                .type(productDTO.getType())
                .price(productDTO
                .getPrice())
                .qtde(productDTO.getQtde())
                .build();
    }

    //Response
    public ResponseProductDTO toProductDTO(Product product){
        return new ResponseProductDTO(product);
    }
    public List<ResponseProductDTO> toProductDTO(List<Product> productList){
        return productList.stream().map(ResponseProductDTO::new).collect(Collectors.toList());
    }

    public void updateProductData(Product product, RequestProductDTO productDTO){

        product.setName(productDTO.getName());
        product.setType(productDTO.getType());
        product.setPrice(productDTO.getPrice());
        product.setQtde(productDTO.getQtde());
    }

}
