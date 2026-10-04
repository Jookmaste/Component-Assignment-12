package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.entity.AuctionItem;
import se331.lab.repository.AuctionItemRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuctionItemDaoImpl implements AuctionItemDao {
    final AuctionItemRepository auctionItemRepository;

    @Override
    public Integer getAuctionItemSize() {
        return (int) auctionItemRepository.count();
    }

    @Override
    public Page<AuctionItem> getAuctionItems(Integer perPage, Integer page) {
        return auctionItemRepository.findAll(PageRequest.of(page, perPage));
    }

    @Override
    public Optional<AuctionItem> getAuctionItem(Long id) {
        return auctionItemRepository.findById(id);
    }

    @Override
    public AuctionItem save(AuctionItem auctionItem) {
        return auctionItemRepository.save(auctionItem);
    }

    @Override
    public Page<AuctionItem> getAuctionItems(String description, Pageable pageable) {
        return auctionItemRepository.findByDescriptionContainingIgnoreCase(description, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double value, Pageable pageable) {
        return auctionItemRepository.findBySuccessfulBidAmountLessThan(value, pageable);
    }
}