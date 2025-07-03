package com.vish.posBookApi.controller;

import com.vish.posBookApi.dto.EventDTO;
import com.vish.posBookApi.dto.EventRequestDTO;
import com.vish.posBookApi.dto.PositionResponseDTO;
import com.vish.posBookApi.model.Events;
import com.vish.posBookApi.service.PositionBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PositionBookController {

    private final PositionBookService service;


    @GetMapping("/positions")
    @CrossOrigin
    public ResponseEntity<PositionResponseDTO> getPositions(){
        PositionResponseDTO response = service.getAllPosition();
        if (response.getPositions() == null) {
            response = new PositionResponseDTO(new ArrayList<>());
        }
        return ResponseEntity.ok(response);
    }


    @PostMapping("/events")
    @CrossOrigin
    public ResponseEntity<PositionResponseDTO> postEvents(@RequestBody EventRequestDTO requests){
        List<EventDTO> events = requests.getEvents();
        if(events == null || events.isEmpty()) {
            return ResponseEntity.badRequest().body(new PositionResponseDTO(new ArrayList<>()));
        }
        else{
            boolean processed = service.processEvents(events);
            if (processed) {
                PositionResponseDTO response = service.getAllPosition();
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(500).body(new PositionResponseDTO(new ArrayList<>()));
            }
        }

    }

}
