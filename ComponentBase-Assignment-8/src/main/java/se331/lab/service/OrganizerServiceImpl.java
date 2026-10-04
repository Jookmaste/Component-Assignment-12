package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.dao.OrganizerDao;
import se331.lab.entity.Organizer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
    final OrganizerDao organizerDao;

    @Override
    public List<Organizer> getAllOrganizer() {
        return organizerDao.getOrganizers(Pageable.unpaged()).getContent();
    }

    @Override
    public Page<Organizer> getOrganizers(Integer perPage, Integer page) {
        return organizerDao.getOrganizers(PageRequest.of(page, perPage));
    }

    @Override
    public Organizer save(Organizer organizer) {
        organizer.setId(null);
        return organizerDao.save(organizer);
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerDao.getOrganizer(id);
    }
}
