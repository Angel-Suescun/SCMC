package com.scmc.controller;


import com.scmc.domain.dto.EncryptRequest;
import com.scmc.domain.dto.EncryptResponse;
import com.scmc.service.EncryptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/encrypt")
public class EncryptController {

  private final EncryptService encryptService;

  public EncryptController(EncryptService encryptService) {
    this.encryptService = encryptService;
  }

  @PostMapping
  public ResponseEntity<?> encrypt( @RequestBody EncryptRequest request) {

    try {
      EncryptResponse response = encryptService.encrypt(request);
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(response);
    } catch (IllegalStateException | IllegalArgumentException exception){

      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(exception.getMessage());

    } catch (Exception exception) {

      return ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(exception.getMessage());

    }

  }
}
