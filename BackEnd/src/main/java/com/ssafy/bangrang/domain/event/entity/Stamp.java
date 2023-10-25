package com.ssafy.bangrang.domain.event.entity;

import com.ssafy.bangrang.global.common.entity.CommonEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stamp")
public class Stamp extends CommonEntity {

    @EmbeddedId
    private StampKey stampKey;
}
