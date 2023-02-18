package tech.bcs.switchmon.generic.config;

import lombok.Data;

import java.util.List;

@Data
public class Contract {

    String address;
    String name;

    List<Event> events;

}
