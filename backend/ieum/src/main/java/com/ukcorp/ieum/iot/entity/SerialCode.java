package com.ukcorp.ieum.iot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "SERIAL_CODE")
public class SerialCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE_NO")
    private Long codeNo;

    @Column(name = "SERIAL_CODE")
    private String serialCode;

    @Column(name = "USABLE")
    @Enumerated(EnumType.STRING)
    private Usable usable;


    public void updateUsableActice() {
        this.usable = Usable.ACTIVE;
    }

    public void updateUsableInactive() {
        this.usable = Usable.INACTIVE;
    }
}
