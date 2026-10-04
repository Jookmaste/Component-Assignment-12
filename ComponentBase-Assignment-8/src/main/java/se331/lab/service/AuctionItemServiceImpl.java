package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.dao.AuctionItemDao;
import se331.lab.entity.AuctionItem;

@Service
@RequiredArgsConstructor
public class AuctionItemServiceImpl implements AuctionItemService {
    final AuctionItemDao auctionItemDao;

    @Override
    public Integer getAuctionItemSize() {
        return auctionItemDao.getAuctionItemSize();
    }

    @Override
    public Page<AuctionItem> getAuctionItems(Integer perPage, Integer page) {
        return auctionItemDao.getAuctionItems(perPage, page);
    }

    @Override
    public AuctionItem getAuctionItem(Long id) {
        return auctionItemDao.getAuctionItem(id).orElse(null);
    }

    @Override
    public AuctionItem save(AuctionItem auctionItem) {
        return auctionItemDao.save(auctionItem);
    }

    @Override
    public Page<AuctionItem> getAuctionItems(String description, Pageable pageable) {
        return auctionItemDao.getAuctionItems(description, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsBySuccessfulBidLessThan(Double value, Pageable pageable) {
        return auctionItemDao.getAuctionItemsBySuccessfulBidLessThan(value, pageable);
    }
}