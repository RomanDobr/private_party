package org.javaacademy.privat_party.entity;

import lombok.Data;
import java.util.List;

@Data
public class GuestList {
  private final List<String> nameGuests;
}
