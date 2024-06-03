package com.ssafy.choonong.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "crop")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CropEntity {
    @Id
    @Column(name="crop_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cropId; //작물 ID

    @Column(name = "crop_name")
    private String cropName; //작물명
}
