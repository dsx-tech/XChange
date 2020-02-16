package org.knowm.xchange.kraken.futures.service;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import javax.ws.rs.FormParam;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/** @author Benedikt Bünz */
public class KrakenFuturesDigest extends BaseParamsDigest {

  /**
   * Constructor
   *
   * @param secretKeyBase64
   * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded
   *     key is invalid).
   */
  private KrakenFuturesDigest(byte[] secretKeyBase64) {

    super(secretKeyBase64, HMAC_SHA_512);
  }

  public static KrakenFuturesDigest createInstance(String secretKeyBase64) {

    if (secretKeyBase64 != null) {
      return new KrakenFuturesDigest(Base64.getDecoder().decode(secretKeyBase64.getBytes()));
    } else return null;
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    MessageDigest sha256;
    try {
      sha256 = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(
          "Illegal algorithm for post body digest. Check the implementation.");
    }
    sha256.update(restInvocation.getParamValue(FormParam.class, "nonce").toString().getBytes());
    sha256.update(restInvocation.getRequestBody().getBytes());

    Mac mac512 = getMac();
    mac512.update(("/" + restInvocation.getPath()).getBytes());
    mac512.update(sha256.digest());

    return Base64.getEncoder().encodeToString(mac512.doFinal()).trim();
  }
}
