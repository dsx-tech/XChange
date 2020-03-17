package org.knowm.xchange.bequant.v2.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bequant.v2.BequantAuthenticated;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ClientConfig;
import si.mazi.rescu.ClientConfigUtil;
import si.mazi.rescu.RestProxyFactory;

public class BequantBaseService extends BaseExchangeService implements BaseService {

  protected final BequantAuthenticated bequant;

  protected BequantBaseService(Exchange exchange) {

    super(exchange);

    String apiKey = exchange.getExchangeSpecification().getApiKey();
    String secretKey = exchange.getExchangeSpecification().getSecretKey();

    ClientConfig config = getClientConfig();
    ClientConfigUtil.addBasicAuthCredentials(config, apiKey, secretKey);
    bequant =
        RestProxyFactory.createProxy(
            BequantAuthenticated.class, exchange.getExchangeSpecification().getSslUri(), config);
  }
}
