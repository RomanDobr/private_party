package org.javaacademy.privat_party.entity;

import lombok.Data;

@Data
public class Guest {
  private final String name;
  private final String email;
  private final String phone;
}
