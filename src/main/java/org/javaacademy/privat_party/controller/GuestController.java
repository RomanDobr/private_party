package org.javaacademy.privat_party.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.privat_party.dto.GuestDto;
import org.javaacademy.privat_party.dto.GuestListDtoRs;
import org.javaacademy.privat_party.entity.Employee;
import org.javaacademy.privat_party.entity.Guest;
import org.javaacademy.privat_party.entity.GuestList;
import org.javaacademy.privat_party.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {
  private final GuestService guestService;
  @PostMapping("/new")
  public ResponseEntity<String> addGuest(@RequestBody GuestDto guestDto,
                                         @RequestHeader String login,
                                         @RequestHeader String password)
          throws SQLException, ClassNotFoundException {
    guestService.createGuest(guestDtoConvert(guestDto), createUser(login, password));
    return ResponseEntity.status(HttpStatus.CREATED).body("Гость успешно внесен в список приглашенных посетителей");
  }

  @GetMapping()
  public ResponseEntity<GuestListDtoRs> findAllGest(@RequestHeader String login, @RequestHeader String password)
          throws SQLException, ClassNotFoundException {
    return ResponseEntity.ok()
            .body(listAllGuestHelper(guestService.findAllGuest(createUser(login, password))));
  }

  private Employee createUser(String login, String password) {
    if (login != null && password != null) {
          return new Employee(login, password);
    }
    return new Employee("null", "null");
  }

  private Guest guestDtoConvert(GuestDto guestDto) {
    return new Guest(guestDto.getName(),
              guestDto.getEmail(),
              guestDto.getPhone());
  }

  private GuestListDtoRs listAllGuestHelper(GuestList guestList) {
    return new GuestListDtoRs(guestList.getNameGuests());
  }
}
