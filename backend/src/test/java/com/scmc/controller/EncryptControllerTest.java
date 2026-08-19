package com.scmc.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.scmc.domain.dto.EncryptRequest;
import com.scmc.domain.dto.EncryptResponse;
import com.scmc.domain.dto.constants.CipherConstants;
import com.scmc.service.EncryptService;
import java.net.URI;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class EncryptControllerTest {

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @Mock
  private EncryptService encryptService;

  @InjectMocks
  private EncryptController encryptController;

  @BeforeEach
  public void setUp() {

    mockMvc = MockMvcBuilders.standaloneSetup(encryptController).build();

    objectMapper = new ObjectMapper();
  }

  @Test
  public void shouldReturnOkWhenEncryptionIsSuccessful() throws Exception {

    String message = "Hola";
    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 3;

    EncryptRequest encryptRequest = new EncryptRequest(
        message,
        blockSize,
        permutation,
        shift
    );


    String paddedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;
    String permutedMessage = "oHla" + CipherConstants.PADDING_CHARACTER;
    String encryptedMessage = "Krod" + CipherConstants.PADDING_CHARACTER;

    EncryptResponse encryptResponse = new EncryptResponse(
        message,
        paddedMessage,
        permutedMessage,
        encryptedMessage,
        blockSize,
        permutation,
        shift,
        List.of()
    );

    when(encryptService.encrypt(encryptRequest)).thenReturn(encryptResponse);

    mockMvc.perform(
        post(URI.create("/api/encrypt"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(encryptRequest))
    )
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnBadRequestWhenIllegalArgumentExceptionOccurs() throws Exception {

    String message = "Hola";
    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 5;

    EncryptRequest encryptRequest = new EncryptRequest(
        message,
        blockSize,
        permutation,
        shift
    );

    when(encryptService.encrypt(encryptRequest))
        .thenThrow(new IllegalArgumentException("Datos Ingresados no válidos"));

    mockMvc.perform(
        post(URI.create("/api/encrypt"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(encryptRequest))
    )
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Datos Ingresados no válidos"));
  }

  @Test
  public void shouldReturnBadRequestWhenIllegalStateExceptionOccurs() throws Exception {

    String message = "Hola";
    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 5;

    EncryptRequest encryptRequest = new EncryptRequest(
        message,
        blockSize,
        permutation,
        shift
    );

    when(encryptService.encrypt(encryptRequest))
        .thenThrow(new IllegalStateException("Estado invalido"));

    mockMvc.perform(
        post(URI.create("/api/encrypt"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(encryptRequest))
    )
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Estado invalido"));
  }

  @Test
  public void shouldReturnInternalServerErrorWhenExceptionOccurs() throws Exception {

    String message = "Hola";
    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 5;

    EncryptRequest encryptRequest = new EncryptRequest(
        message,
        blockSize,
        permutation,
        shift
    );

    when(encryptService.encrypt(encryptRequest))
        .thenThrow(new RuntimeException("Error interno del servidor"));

    mockMvc.perform(
        post(URI.create("/api/encrypt"))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(encryptRequest))
    )
        .andExpect(status().isInternalServerError())
        .andExpect(content().string("Error interno del servidor"));
  }

}
