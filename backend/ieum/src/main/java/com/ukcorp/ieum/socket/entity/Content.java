package com.ukcorp.ieum.socket.entity;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.ukcorp.ieum.socket.dto.response.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private String type;
    private Response content;

    public void pillToContent(PillResponseDto data) throws JsonProcessingException {
        this.type = "Pill";
        this.content = data;
    }

    public void mealToContent(MealResponseDto data) throws JsonProcessingException {
        this.type = "Meal";
        this.content = data;
    }

    public void messageToContent() {
        this.type = "Message";
    }

    public void sleepToContent(SleepResponseDto data) throws JsonProcessingException {
        this.type = "Sleep";
        this.content = data;
    }

    public void eventToContent(EventResponseDto data) throws JsonProcessingException {
        this.type = "Event";
        this.content = data;
    }
}
