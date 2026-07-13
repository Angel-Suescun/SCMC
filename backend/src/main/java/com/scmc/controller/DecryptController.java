package com.scmc.controller;

import com.scmc.domain.dto.DecryptRequest;
import com.scmc.domain.dto.DecryptResponse;
import com.scmc.service.DecryptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decrypt")
public class DecryptController {

  private final DecryptService decryptService;

  public DecryptController(DecryptService decryptService) {

    this.decryptService = decryptService;

  }

  @PostMapping
  public ResponseEntity<?> decrypt(@RequestBody DecryptRequest request) {

    try {

      DecryptResponse response = decryptService.decrypt(request);

      return ResponseEntity
          .status(HttpStatus.OK)
          .body(response);

    } catch (IllegalStateException exception) {

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
