package com.cg.service;

import com.cg.dto.ProductDTO;
import com.cg.model.Product;

import java.util.List;

public interface IProductService extends IGeneralService<Product>{
    List<ProductDTO> findAllProductDTO();

}
