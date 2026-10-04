package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import se331.lab.dao.ParticipantDao;
import se331.lab.entity.Participant;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    final ParticipantDao participantDao;

    @Override
    public List<Participant> findAll() {
        return participantDao.findAll();
    }
}
