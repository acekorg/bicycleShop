package com.aleksandar.core.service;

import com.aleksandar.core.model.admin.PartAdminModel;
import com.aleksandar.core.model.admin.ProductAdminModel;
import com.aleksandar.core.model.base.PartCombinationsInvalidityModel;
import com.aleksandar.entity.Part;
import com.aleksandar.entity.PartCombinationsInvalidity;
import com.aleksandar.repository.PartRepository;
import com.aleksandar.repository.ProductRepository;
import com.aleksandar.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for Admin user actions.
 */
@Service
public class AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PartRepository partRepository;

    /**
     * Store new product or edit existing one.
     * .save method is provided by Spring data and if primary key is provided saves the existing object, otherwise creates new one.
     * @param productModel Model that comes from the Admin UI.
     * @return Product ID.
     */
    public Long storeProduct(ProductAdminModel productModel) {

        Product product = new Product();

        product.setProductType(productModel.getProductType());
        product.setBrand(productModel.getBrand());
        product.setModel(productModel.getModel());
        product.setDescription(productModel.getDescription());

        List<PartAdminModel> partAdminModelList = productModel.getPartAdminModelList();
        product.setParts(mapPartModelListToEntityList(partAdminModelList));

        List<PartCombinationsInvalidityModel> partCombinationsInvalidityList =
                productModel.getPartCombinationsInvalidityList();

        product.setPartCombinationsInvalidities(mapPartCombinationsInvalidityListToEntityList(partCombinationsInvalidityList));

        Product savedBicycle = productRepository.save(product);

        return savedBicycle.getId();
    }

    /**
     * Store new part or edit existing one.
     * @param partModel Model that comes from the Admin UI.
     * @return Part ID.
     */
    public Long storePart(PartAdminModel partModel) {

        Part part = new Part();

        part.setPartType(partModel.getPartType());
        part.setName(partModel.getName());
        part.setBasePrice(partModel.getBasePrice());
        part.setStock(partModel.getStock());

        Part savedPart = partRepository.save(part);
        return savedPart.getId();
    }

    /**
     * Just a simple mapping method
     * @param partAdminModelList Input list
     * @return Output list
     */
    private List<Part> mapPartModelListToEntityList(List<PartAdminModel> partAdminModelList) {

        List<Part> partList = new ArrayList<>();

        partAdminModelList.forEach(partAdminModel -> {
            partList.add(Part.builder()
                    .id(partAdminModel.getId())
                    .partType(partAdminModel.getPartType())
                    .name(partAdminModel.getName())
                    .basePrice(partAdminModel.getBasePrice())
                    .stock(partAdminModel.getStock())
                    .build());
        });

        return partList;
    }

    /**
     * Just a simple mapping method
     * @param partCombinationsInvalidityList Input list
     * @return Output list
     */
    private List<PartCombinationsInvalidity> mapPartCombinationsInvalidityListToEntityList(
            List<PartCombinationsInvalidityModel> partCombinationsInvalidityList) {

        List<PartCombinationsInvalidity> partCombinationsInvalidityEntityList = new ArrayList<>();

        partCombinationsInvalidityList.forEach(partCombinationsInvalidity -> {
            partCombinationsInvalidityEntityList.add(PartCombinationsInvalidity.builder()
                    .id(partCombinationsInvalidity.getId())
                    .firstPartId(partCombinationsInvalidity.getFirstPartId())
                    .secondPartId(partCombinationsInvalidity.getSecondPartId())
                    .productId(partCombinationsInvalidity.getProductId())
                    .build());
        });

        return partCombinationsInvalidityEntityList;
    }
}
