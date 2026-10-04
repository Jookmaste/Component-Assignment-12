package se331.lab.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import se331.lab.entity.Organizer;
import se331.lab.service.OrganizerService;
import se331.lab.util.LabMapper;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class OrganizerController {
    final OrganizerService organizerService;

    @GetMapping("/organizers")
    public ResponseEntity<?> getOrganizer() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDto(organizerService.getAllOrganizer()));
    }

    @GetMapping("/organizers/{id}")
    public ResponseEntity<?> getOrganizerById(@PathVariable("id") Long id) {
        Organizer organizer = organizerService.getOrganizer(id);
        if (organizer != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDto(organizer));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found");
        }
    }

    @PostMapping("/organizers")
    public ResponseEntity<?> saveOrganizer(@RequestBody Organizer organizer) {
        Organizer savedOrganizer = organizerService.save(organizer);
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDto(savedOrganizer)); 
    }
}
