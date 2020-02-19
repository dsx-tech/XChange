package org.knowm.xchange.krakenFutures.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import org.knowm.xchange.exceptions.ExchangeException;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestInvocation;

/** @author pchertalev */
public class KrakenFuturesDigest implements ParamsDigest {

  private String apiPrivateKey;

  public KrakenFuturesDigest(String apiPrivateKey) {
    this.apiPrivateKey = apiPrivateKey;
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {
    Map<String, String> headersMap =
        restInvocation.getParamsMap().get(HeaderParam.class).asHttpHeaders();
    Map<String, String> queryParamsMap =
        restInvocation.getParamsMap().get(QueryParam.class).asHttpHeaders();
    String postData =
        queryParamsMap.entrySet().stream()
            .map(e -> e.getKey() + "=" + e.getValue())
            .collect(Collectors.joining("&"));
    return signMessage(restInvocation.getPath(), headersMap.get("Nonce"), postData);
  }

  // Signs a message
  private String signMessage(String endpoint, String nonce, String postData) {
    try {
      // Step 1: concatenate postData, nonce + endpoint
      String message = postData + nonce + endpoint;

      // Step 2: hash the result of step 1 with SHA256
      byte[] hash =
          MessageDigest.getInstance("SHA-256").digest(message.getBytes(StandardCharsets.UTF_8));

      // step 3: base64 decode apiPrivateKey
      byte[] secretDecoded = Base64.getDecoder().decode(apiPrivateKey);

      // step 4: use result of step 3 to hash the resultof step 2 with
      // HMAC-SHA512
      Mac hmacsha512 = Mac.getInstance("HmacSHA512");
      hmacsha512.init(new SecretKeySpec(secretDecoded, "HmacSHA512"));
      byte[] hash2 = hmacsha512.doFinal(hash);

      // step 5: base64 encode the result of step 4 and return
      return Base64.getEncoder().encodeToString(hash2);
    } catch (Exception e) {
      throw new ExchangeException("Can't create signature: " + e.getMessage(), e);
    }
  }
}
