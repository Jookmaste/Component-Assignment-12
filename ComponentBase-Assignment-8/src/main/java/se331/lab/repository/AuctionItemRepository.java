package se331.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se331.lab.entity.AuctionItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Long> {
    
    Page<AuctionItem> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);

    @Query("SELECT a FROM AuctionItem a WHERE a.successfulBid.amount < :value")
    Page<AuctionItem> findBySuccessfulBidAmountLessThan(@Param("value") Double value, Pageable pageable);
}