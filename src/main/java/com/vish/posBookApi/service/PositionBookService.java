package com.vish.posBookApi.service;

import com.vish.posBookApi.dto.EventDTO;
import com.vish.posBookApi.dto.PositionDTO;
import com.vish.posBookApi.dto.PositionResponseDTO;
import com.vish.posBookApi.model.Events;
import com.vish.posBookApi.model.Positions;
import com.vish.posBookApi.repository.Event;
import com.vish.posBookApi.repository.Position;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionBookService {

    @Autowired
    private Event eventRepo;

    @Autowired
    private Position positionRepo;

    private PositionDTO buildPositionDTO(Positions pos){
        List<Events> allEvents = eventRepo.findByAccountAndSecurity(pos.getAccount(), pos.getSecurity());
        List<EventDTO> events = new ArrayList<>();

        for (Events e : allEvents) {
            EventDTO eventDTO = new EventDTO(e.getId(), e.getAccount(), e.getSecurity(), e.getAction(), e.getQuantity());
            events.add(eventDTO);
        }

        return new PositionDTO(pos.getId(),pos.getAccount(), pos.getSecurity(), pos.getQuantity(), events);
    }

    // Get All Positions
    @Transactional
    public PositionResponseDTO getAllPosition(){
        List<Positions> positions = positionRepo.findAll();
        List<PositionDTO> list = new ArrayList<>();
        for (Positions pos : positions) {
            list.add(buildPositionDTO(pos));
        }

        return new PositionResponseDTO(list);
    }

    @Transactional
    public  Boolean processEvents(List<EventDTO> events){
        List<Events> eventList = new ArrayList<>();
        // Save all events to the repository
        for (EventDTO event : events) {
            Events e = new Events(event.getId(), event.getAction(), event.getAccount(), event.getSecurity(), event.getQuantity());
            e.setId(null);  // Ensure new event has no ID to create a new record
            eventList.add(e);
            eventRepo.save(e);
        }


        for (EventDTO e : events) {
            String action = e.getAction().toUpperCase();
            if ("CANCEL".equalsIgnoreCase(action)) {
                // Find the event to cancel
                try {
                    Events existingEvent = eventRepo.findById(e.getEventId()).orElseThrow(() -> new RuntimeException("Event not found"));
                    if (existingEvent != null) {
                        // Update the position by reversing the action
                        Positions pos = (Positions) positionRepo.findByAccountAndSecurity(e.getAccount(), e.getSecurity());
                        if (pos != null) {
                            int qty = "BUY".equalsIgnoreCase(existingEvent.getAction()) ? -existingEvent.getQuantity() : existingEvent.getQuantity();
                            pos.setQuantity(pos.getQuantity() + qty);
                            positionRepo.save(pos);
                        }
                    }
                }
                catch (Exception ex) {
                    System.err.println("Error cancelling event: " + ex.getMessage());
                }

            } else if ("BUY".equalsIgnoreCase(action) || "SELL".equalsIgnoreCase(action)) {
                int qty = "BUY".equalsIgnoreCase(action) ? e.getQuantity() : -e.getQuantity();
                Positions pos = (Positions) positionRepo.findByAccountAndSecurity(e.getAccount(), e.getSecurity());
                if(pos == null) {
                    pos = new Positions();
                    pos.setAccount(e.getAccount());
                    pos.setSecurity(e.getSecurity());
                    pos.setQuantity(qty);
                }
                else {
                    pos.setQuantity(pos.getQuantity() + qty);
                }
                positionRepo.save(pos);
            }
        }
        return true;
    }

}
