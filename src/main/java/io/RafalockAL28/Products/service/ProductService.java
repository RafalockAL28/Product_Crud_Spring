package io.RafalockAL28.Products.service;

import io.RafalockAL28.Products.dto.request.RequestProductDTO;
import io.RafalockAL28.Products.dto.response.ResponseProductDTO;

import java.util.List;

public interface ProductService {


    ResponseProductDTO findById(Long id);

    List<ResponseProductDTO> findAll();

    ResponseProductDTO create(RequestProductDTO productDTO);

    ResponseProductDTO update(RequestProductDTO productDTO, Long id);

    String delete(Long id);


}
