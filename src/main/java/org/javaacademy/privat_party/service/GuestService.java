package org.javaacademy.privat_party.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.privat_party.entity.Employee;
import org.javaacademy.privat_party.entity.Guest;
import org.javaacademy.privat_party.entity.GuestList;
import org.javaacademy.privat_party.repository.GuestRepository;
import org.springframework.stereotype.Service;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class GuestService {
  private final GuestRepository guestRepository;
  public void createGuest(Guest guest, Employee employee) throws SQLException, ClassNotFoundException {
    guestRepository.addGuest(guest.getName(),
                guest.getEmail(),
                guest.getPhone(),
                employee.getLogin(),
                employee.getPassword());
  }

  public GuestList findAllGuest(Employee employee) throws SQLException, ClassNotFoundException {
    return guestRepository.findAllGuest(employee.getLogin(), employee.getPassword());
  }
}
