package org.javaacademy.privat_party.dto;

import lombok.Data;
import java.util.List;

@Data
public class GuestListDtoRs {
    private final List<String> nameGuests;
}
