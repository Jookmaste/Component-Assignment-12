package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Participant;
import se331.lab.repository.ParticipantRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ParticipantDaoImpl implements ParticipantDao {
    final ParticipantRepository participantRepository;

    @Override
    public List<Participant> findAll() {
        return participantRepository.findAll(Pageable.unpaged()).toList();
    }
}
