package com.ankit.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
//to store the details of hotel which is coming from HOTEL-SERVICE
    private  String id;
    private  String name;
    private  String location;
    private  String about;

}