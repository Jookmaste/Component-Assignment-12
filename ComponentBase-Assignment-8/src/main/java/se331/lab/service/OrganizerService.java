package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.Organizer;

import java.util.List;

public interface OrganizerService {
    List<Organizer> getAllOrganizer();
    public Page<Organizer> getOrganizers(Integer perPage, Integer page);
    Organizer save(Organizer organizer);
    Organizer getOrganizer(Long id);
}
