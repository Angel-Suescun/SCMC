package com.scmc.service.algorithm;

import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import com.scmc.domain.dto.constants.CipherConstants;


@Service
public class PaddingDecryptService {

  public String decrypt(String message) {

    return message.replaceAll(Pattern.quote(String.valueOf(
        CipherConstants.PADDING_CHARACTER) + "+$"), "");
  }
}
