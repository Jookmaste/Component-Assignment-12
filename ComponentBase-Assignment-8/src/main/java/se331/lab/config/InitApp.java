package se331.lab.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.entity.Participant;
import se331.lab.repository.EventRepository;
import se331.lab.repository.OrganizerRepository;
import se331.lab.repository.ParticipantRepository;
import se331.lab.entity.AuctionItem;
import se331.lab.entity.Bid;
import se331.lab.repository.AuctionItemRepository;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;
    final AuctionItemRepository auctionItemRepository;

    @Override
    @Transactional
    public void onApplicationEvent (ApplicationReadyEvent applicationReadyEvent) {
        Participant part1, part2, part3, part4, part5;
        part1 = participantRepository.save(Participant.builder()
                .name("participant1")
                .telNo("0123456789")
                .build());
        part2 = participantRepository.save(Participant.builder()
                .name("participant2")
                .telNo("0123456789")
                .build());
        part3 = participantRepository.save(Participant.builder()
                .name("participant3")
                .telNo("0123456789")
                .build());
        part4 = participantRepository.save(Participant.builder()
                .name("participant4")
                .telNo("0123456789")
                .build());
        part5 = participantRepository.save(Participant.builder()
                .name("participant5")
                .telNo("0123456789")
                .build());
        List<Participant> participants = new ArrayList<>();
        participants.add(part1);
        participants.add(part2);
        participants.add(part3);
        participants.add(part4);
        participants.add(part5);

        Organizer org1, org2, org3;
        org1 = organizerRepository.save(Organizer.builder()
                .name("CAMT")
                .build());
        org2 = organizerRepository.save(Organizer.builder()
                .name("CMU")
                .build());
        org3 = organizerRepository.save(Organizer.builder()
                .name("ChiangMai")
                .build());
        Event tempEvent;
        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petsAllowed(false)
                .participants(participants)
                .build());
        tempEvent.setOrganizer(org1);
        part1.getEventHistories().add(tempEvent);
        part2.getEventHistories().add(tempEvent);
        part4.getEventHistories().add(tempEvent);
        org1.getOwnEvents().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petsAllowed(false)
                .participants(participants)
                .build());
        tempEvent.setOrganizer(org1);
        part1.getEventHistories().add(tempEvent);
        part2.getEventHistories().add(tempEvent);
        part3.getEventHistories().add(tempEvent);
        part4.getEventHistories().add(tempEvent);
        org1.getOwnEvents().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00am-10.00 pm.")
                .petsAllowed(false)
                .participants(participants)
                .build());
        tempEvent.setOrganizer(org2);
        part1.getEventHistories().add(tempEvent);
        part2.getEventHistories().add(tempEvent);
        part3.getEventHistories().add(tempEvent);
        org2.getOwnEvents().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am-6.00 pm.")
                .petsAllowed(true)
                .participants(participants)
                .build());
        tempEvent.setOrganizer(org3);
        part1.getEventHistories().add(tempEvent);
        part2.getEventHistories().add(tempEvent);
        part3.getEventHistories().add(tempEvent);
        part4.getEventHistories().add(tempEvent);
        part5.getEventHistories().add(tempEvent);
        org3.getOwnEvents().add(tempEvent);

        AuctionItem item1 = AuctionItem.builder()
                .description("Antique Rolex Watch")
                .type("Watch")
                .build();
        Bid bid1_1 = Bid.builder().amount(1500.0).datetime(LocalDateTime.now().minusDays(3)).item(item1).bidder(part1).build();
        Bid bid1_2 = Bid.builder().amount(2000.0).datetime(LocalDateTime.now().minusDays(2)).item(item1).bidder(part2).build();
        Bid bid1_3 = Bid.builder().amount(2500.0).datetime(LocalDateTime.now().minusDays(1)).item(item1).bidder(part3).build();
        item1.getBids().add(bid1_1);
        item1.getBids().add(bid1_2);
        item1.getBids().add(bid1_3);
        item1.setSuccessfulBid(bid1_3);
        auctionItemRepository.save(item1);

        AuctionItem item2 = AuctionItem.builder()
                .description("Vintage Painting by Picasso")
                .type("Art")
                .build();
        Bid bid2_1 = Bid.builder().amount(5000.0).datetime(LocalDateTime.now().minusDays(4)).item(item2).bidder(part2).build();
        Bid bid2_2 = Bid.builder().amount(7500.0).datetime(LocalDateTime.now().minusDays(2)).item(item2).bidder(part3).build();
        Bid bid2_3 = Bid.builder().amount(10000.0).datetime(LocalDateTime.now()).item(item2).bidder(part1).build();
        item2.getBids().add(bid2_1);
        item2.getBids().add(bid2_2);
        item2.getBids().add(bid2_3);
        item2.setSuccessfulBid(bid2_3);
        auctionItemRepository.save(item2);

        AuctionItem item3 = AuctionItem.builder()
                .description("Rare Pokemon Card 1st Edition")
                .type("Collectible")
                .build();
        Bid bid3_1 = Bid.builder().amount(300.0).datetime(LocalDateTime.now().minusDays(5)).item(item3).bidder(part4).build();
        Bid bid3_2 = Bid.builder().amount(600.0).datetime(LocalDateTime.now().minusDays(3)).item(item3).bidder(part5).build();
        Bid bid3_3 = Bid.builder().amount(900.0).datetime(LocalDateTime.now().minusDays(1)).item(item3).bidder(part1).build();
        item3.getBids().add(bid3_1);
        item3.getBids().add(bid3_2);
        item3.getBids().add(bid3_3);
        item3.setSuccessfulBid(bid3_3);
        auctionItemRepository.save(item3);

        AuctionItem item4 = AuctionItem.builder()
                .description("Classic Acoustic Guitar")
                .type("Instrument")
                .build();
        Bid bid4_1 = Bid.builder().amount(100.0).datetime(LocalDateTime.now().minusDays(3)).item(item4).bidder(part2).build();
        Bid bid4_2 = Bid.builder().amount(150.0).datetime(LocalDateTime.now().minusDays(2)).item(item4).bidder(part3).build();
        Bid bid4_3 = Bid.builder().amount(200.0).datetime(LocalDateTime.now().minusDays(1)).item(item4).bidder(part4).build();
        item4.getBids().add(bid4_1);
        item4.getBids().add(bid4_2);
        item4.getBids().add(bid4_3);
        auctionItemRepository.save(item4);

        AuctionItem item5 = AuctionItem.builder()
                .description("Signed Basketball by Michael Jordan")
                .type("Sports")
                .build();
        Bid bid5_1 = Bid.builder().amount(1200.0).datetime(LocalDateTime.now().minusDays(3)).item(item5).bidder(part5).build();
        Bid bid5_2 = Bid.builder().amount(1800.0).datetime(LocalDateTime.now().minusDays(2)).item(item5).bidder(part1).build();
        Bid bid5_3 = Bid.builder().amount(2200.0).datetime(LocalDateTime.now().minusDays(1)).item(item5).bidder(part2).build();
        item5.getBids().add(bid5_1);
        item5.getBids().add(bid5_2);
        item5.getBids().add(bid5_3);
        auctionItemRepository.save(item5);
    }
}
