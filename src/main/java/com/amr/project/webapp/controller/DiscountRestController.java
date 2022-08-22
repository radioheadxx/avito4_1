package com.amr.project.webapp.controller;

import com.amr.project.converter.DiscountMapper;
import com.amr.project.exception.ErrorMessage;
import com.amr.project.exception.InvalidDiscountException;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Discount;
import com.amr.project.service.abstracts.DiscountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/discount")
public class DiscountRestController {

    DiscountService discountService;
    DiscountMapper discountMapper;

    public DiscountRestController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Get discount with ID")
    ResponseEntity<DiscountDto> getDiscountById(@PathVariable Long id) {
        if(!discountService.existsById(id)) {
            Date date = new Date();
            throw new InvalidDiscountException(new ErrorMessage(204, date, "Not found", "The discount doesn't exist"));
        }
        Discount discount = discountService.findById(id);
        DiscountDto discountDto = discountMapper.toDto(discount);
        return new ResponseEntity<>(discountDto, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("Create new discount")
    void createDiscount(@RequestBody DiscountDto discountDto) {
        if(discountDto.getId() != null) {
            Date date = new Date();
            throw new InvalidDiscountException(new ErrorMessage(204, date, "Can't create", "The discount is already exist"));
        }
        Discount discount = discountService.persist(discountMapper.toModel(discountDto));
        System.out.println(discount);
    }
    @PatchMapping
    @ApiOperation("Update discount")
    void updateDiscount(@RequestBody DiscountDto discountDto) {
        if(discountDto.getId() == null) {
            Date date = new Date();
            throw new InvalidDiscountException(new ErrorMessage(204, date, "Can't update", "The ID of discount can't be null"));
        }
        discountService.update(discountMapper.toModel(discountDto));
    }
    @DeleteMapping("/{id}")
    @ApiOperation("Delete discount")
    void deleteDiscount(@PathVariable Long id) {
        if(!discountService.existsById(id)) {
            Date date = new Date();
            throw new InvalidDiscountException(new ErrorMessage(204, date, "Not found", "The discount doesn't exist"));
        }
        discountService.deleteByIdCascadeIgnore(id);
    }
}
