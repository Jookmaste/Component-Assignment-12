package se331.lab.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.AuctionItem;

import java.util.Optional;

public interface AuctionItemDao {
    Integer getAuctionItemSize();
    Page<AuctionItem> getAuctionItems(Integer perPage, Integer page);
    Optional<AuctionItem> getAuctionItem(Long id);
    AuctionItem save(AuctionItem auctionItem);
    Page<AuctionItem> getAuctionItems(String description, Pageable pageable);
    Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double value, Pageable pageable);
}