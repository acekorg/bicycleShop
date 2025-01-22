package com.aleksandar.core.service;

import com.aleksandar.core.model.user.PartUserModel;
import com.aleksandar.entity.Part;
import com.aleksandar.entity.PartCombinationsPricing;
import com.aleksandar.repository.PartCombinationsPricingRepository;
import com.aleksandar.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for Regular user actions.
 */
@Service
public class UserService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartCombinationsPricingRepository partCombinationsPricingRepository;


    /**
     * Method that can resolve prices for user selection of parts (although main idea is that prices are dissolved and
     * calculations should happen on frontend.
     * @param partIds Selected parts IDs.
     * @return List of part data with checkout price.
     */
    public List<PartUserModel> getPartsUserModel(List<Long> partIds) {


        List<Part> partsEntityList = partRepository.findSelectedParts(partIds);

        // This method call returns all the surcharges needed resulting from parts combinations.
        List<PartCombinationsPricing> partCombinationsSurcharges =
                partCombinationsPricingRepository.findPartCombinationsSurcharges(partIds);


        List<PartUserModel> partUserModels = new ArrayList<>();

        partsEntityList.forEach(selectedPart -> {
           PartUserModel partUserModel = new PartUserModel();
           partUserModel.setId(selectedPart.getId());
           partUserModel.setPartType(selectedPart.getPartType());
           partUserModel.setName(selectedPart.getName());
           partUserModel.setStock(selectedPart.getStock());

           BigDecimal basePrice = selectedPart.getBasePrice();
           BigDecimal surchargeForPart = findTotalSurchargesForPart(selectedPart.getId(), partCombinationsSurcharges);
           BigDecimal price = basePrice.add(surchargeForPart);
           partUserModel.setPrice(price);

           partUserModels.add(partUserModel);
        });

        return partUserModels;
    }

    /**
     * Given that partId is main part that's being processed, this method will filter all items from DB where the partId
     * is set as main part and paired part is one of the other selected ones. Then it will sum the surcharge of all
     * occurrences.
     * @param partId The main part id that's being processed.
     * @param partCombinationsSurcharges Part combinations that match the user selection of parts.
     * @return The total surcharge that the Part should add to itself.
     */
    private BigDecimal findTotalSurchargesForPart(Long partId, List<PartCombinationsPricing> partCombinationsSurcharges) {

        BigDecimal totalSurchargeForPart = partCombinationsSurcharges
                .stream()
                .filter(partCombination -> partCombination.getMainPartId().equals(partId))
                .map(PartCombinationsPricing::getSurcharge)
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));

        return totalSurchargeForPart;
    }

}
