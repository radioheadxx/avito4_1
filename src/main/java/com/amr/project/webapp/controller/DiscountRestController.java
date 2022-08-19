package com.amr.project.webapp.controller;

import com.amr.project.converter.DiscountMapper;
import com.amr.project.exception.ErrorMessage;
import com.amr.project.exception.InvalidDiscountException;
import com.amr.project.model.dto.DiscountDto;
import com.amr.project.model.entity.Discount;
import com.amr.project.service.abstracts.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DiscountRestController {

    DiscountService discountService;
    DiscountMapper discountMapper;

    public DiscountRestController(DiscountService discountService) {
        this.discountService = discountService;
    }

    ResponseEntity<DiscountDto> getDiscountById(long id) {
        if(!discountService.existsById(id)) {
            Date date = new Date();
            throw new InvalidDiscountException(new ErrorMessage(204, date, "Not found", "The discount doesn't exist"));
        }
        Discount discount = discountService.findById(id);
        DiscountDto discountDto = discountMapper.toDto(discount);
        return new ResponseEntity<>(discountDto, HttpStatus.OK);
    }
}
