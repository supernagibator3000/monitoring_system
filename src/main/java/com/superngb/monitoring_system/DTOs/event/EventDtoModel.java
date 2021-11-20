package com.superngb.monitoring_system.DTOs.event;

import com.superngb.monitoring_system.Entities.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDtoModel implements Serializable {

    private Long id;
    private String name;
    private Long subject;

    static  public EventDtoModel eventMapper(Event event){
        return new EventDtoModel(event.getId(), event.getName(), event.getSubject().getId());
    }

    static  public List<EventDtoModel> listEventMapper(List<Event> events){
        List<EventDtoModel> eventDtoModels = new ArrayList<>();
        for (Event event: events)
            eventDtoModels.add(eventMapper(event));
        return eventDtoModels;
    }
}
