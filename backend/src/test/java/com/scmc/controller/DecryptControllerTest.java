package com.scmc.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.scmc.domain.dto.DecryptRequest;
import com.scmc.domain.dto.DecryptResponse;
import com.scmc.domain.dto.constants.CipherConstants;
import com.scmc.service.DecryptService;
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
public class DecryptControllerTest {

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @Mock
  private DecryptService decryptService;

  @InjectMocks
  private DecryptController decryptController;

  @BeforeEach
  public void setUp() {

    mockMvc = MockMvcBuilders.standaloneSetup(decryptController).build();

    objectMapper = new ObjectMapper();
  }

  @Test
  public void shouldReturnOkWhenDecryptionIsSuccessful() throws Exception {

    String encryptedMessage = "Krod";
    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 3;

    DecryptRequest request = new DecryptRequest(
        encryptedMessage,
        blockSize,
        permutation,
        shift
    );

    String permutedMessage = "oHla";
    String paddedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;
    String decryptedMessage = "Hola";

    DecryptResponse response = new DecryptResponse(
        encryptedMessage,
        permutedMessage,
        paddedMessage,
        decryptedMessage,
        blockSize,
        permutation,
        shift,
        List.of()
    );

    when(decryptService.decrypt(any(DecryptRequest.class))).thenReturn(response);

    mockMvc.perform(
        post(URI.create("/api/decrypt"))
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request))
    )
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnBadRequestWhenIllegalArgumentExceptionOccurs() throws Exception {

    String encryptedMessage = "Krod";
    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 3;

    DecryptRequest request = new DecryptRequest(
        encryptedMessage,
        blockSize,
        permutation,
        shift
    );

    when(decryptService.decrypt(any(DecryptRequest.class)))
        .thenThrow(new IllegalArgumentException("Datos ingresados no válidos"));

    mockMvc.perform(
        post(URI.create("/api/decrypt"))
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request))
    )
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Datos ingresados no válidos"));

  }

  @Test
  public void shouldReturnBadRequestWhenIllegalStateExceptionOccurs() throws Exception {

    String encryptedMessage = "Krod";
    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 3;

    DecryptRequest request = new DecryptRequest(
        encryptedMessage,
        blockSize,
        permutation,
        shift
    );

    when(decryptService.decrypt(any(DecryptRequest.class)))
        .thenThrow(new IllegalStateException("Estado inválido"));

    mockMvc.perform(
        post(URI.create("/api/decrypt"))
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request))
    )
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Estado inválido"));

  }

  @Test
  public void shouldReturnInternalServerErrorWhenUnexpectedExceptionOccurs() throws Exception {

    String encryptedMessage = "Krod";
    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 3;

    DecryptRequest request = new DecryptRequest(
        encryptedMessage,
        blockSize,
        permutation,
        shift
    );

    when(decryptService.decrypt(any(DecryptRequest.class)))
        .thenThrow(new RuntimeException("Error inesperado"));

    mockMvc.perform(
        post(URI.create("/api/decrypt"))
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(request))
    )
        .andExpect(status().isInternalServerError())
        .andExpect(content().string("Error inesperado"));

  }
}
