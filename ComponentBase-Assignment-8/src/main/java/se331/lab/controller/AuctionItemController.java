package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.lab.entity.AuctionItem;

import se331.lab.service.AuctionItemService;
import se331.lab.util.LabMapper;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuctionItemController {
    final AuctionItemService auctionItemService;

    @GetMapping("/auction-items")
    public ResponseEntity<?> getAuctionItems(
            @RequestParam(value = "_limit", defaultValue = "3") Integer perPage,
            @RequestParam(value = "_page", defaultValue = "1") Integer page,
            @RequestParam(value = "description", required = false) String description) {
        
        Page<AuctionItem> pageOutput;
        if (description != null && !description.isEmpty()) {
            pageOutput = auctionItemService.getAuctionItems(description, PageRequest.of(page - 1, perPage));
        } else if (perPage != null && page != null) {
            pageOutput = auctionItemService.getAuctionItems(perPage, page - 1);
        } else {
            pageOutput = auctionItemService.getAuctionItems(3, 0); // ค่าเริ่มต้น
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        
        return new ResponseEntity<>(
                LabMapper.INSTANCE.getAuctionItemDtoList(pageOutput.getContent()), 
                responseHeaders, 
                HttpStatus.OK
        );
    }

    @GetMapping("/auction-items/{id}")
    public ResponseEntity<?> getAuctionItem(@PathVariable("id") Long id) {
        AuctionItem output = auctionItemService.getAuctionItem(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getAuctionItemDto(output));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/auction-items-by-bid-less-than")
    public ResponseEntity<?> getAuctionItemsByBidLessThan(
            @RequestParam("value") Double value,
            @RequestParam(value = "_limit", defaultValue = "3") Integer perPage,
            @RequestParam(value = "_page", defaultValue = "1") Integer page) {
        
        Page<AuctionItem> pageOutput = auctionItemService.getAuctionItemsBySuccessfulBidLessThan(value, PageRequest.of(page - 1, perPage));

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        
        return new ResponseEntity<>(
                LabMapper.INSTANCE.getAuctionItemDtoList(pageOutput.getContent()), 
                responseHeaders, 
                HttpStatus.OK
        );
    }
}