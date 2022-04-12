package com.itechart.restaurant_info_service.service;

import com.itechart.restaurant_info_service.dto.ItemDTO;
import com.itechart.restaurant_info_service.dto.NewItemDTO;
import com.itechart.restaurant_info_service.model.Item;
import com.itechart.restaurant_info_service.model.Restaurant;
import com.itechart.restaurant_info_service.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Validated
public class ItemService {

    private final ManagerService managerService;
    private final RestaurantService restaurantService;
    private final AwsService awsService;
    private final IngredientService ingredientService;

    private final ItemRepository itemRepository;

    public Page<ItemDTO> getItems(String itemType, String filterName, double filterMinPrice, double filterMaxPrice, String filterRestaurant, Pageable pageable){
        Page<ItemDTO> page = itemRepository.findItemsByItemType(itemType, filterName, filterMinPrice, filterMaxPrice, filterRestaurant, pageable);
        for (var elementOfPage : page) {
            byte[] data = awsService.downloadFile(elementOfPage.getId().toString());
            elementOfPage.setImage(data);
        }
        return page;
    }

    @Transactional
    public Long addItem(NewItemDTO newItemDTO){
        Long restaurantId = managerService.findRestaurantByManagerEmail(newItemDTO.getManagerEmail());
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        Item item = convertNewItemDTOIntoItem(newItemDTO, restaurant);
        item = itemRepository.save(item);
        ingredientService.saveIngredients(newItemDTO.getIngredients(), item.getId());
        return item.getId();
    }

    @Transactional
    public void addImage(MultipartFile image, Long id){
        awsService.uploadFile(image, id);
    }

    private Item convertNewItemDTOIntoItem(NewItemDTO newItemDTO, Restaurant restaurant){
        return Item.builder()
                .restaurant(restaurant)
                .name(newItemDTO.getName())
                .description(newItemDTO.getDescription())
                .itemType(newItemDTO.getItemType())
                .available(newItemDTO.getAvailable())
                .feature(newItemDTO.getFeature())
                .price(newItemDTO.getPrice())
                .discount(newItemDTO.getDiscount())
                .build();
    }



}
