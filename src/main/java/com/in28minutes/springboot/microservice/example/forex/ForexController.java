package com.in28minutes.springboot.microservice.example.forex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {

    private final Environment environment;

    private final ExchangeValueRepository repository;

    private final Logger logger = LoggerFactory.getLogger(ForexController.class);

    /*
     * Field Injection is highly discouraged. Use either constructor or setter injection.
     */
    @Autowired
    public ForexController(Environment environment, ExchangeValueRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(
            @PathVariable String from, @PathVariable String to) {

        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

        System.out.println(environment.getProperty("server.port") + " " +
        environment.getProperty("spring.application.name"));

        String portStr = environment.getProperty("server.port");
        logger.info("Port String : {}", portStr);
        int port = Integer.parseInt(portStr == null ? "-1": portStr);
        logger.info("Port int : {}", port);

        exchangeValue.setPort(port);

        return exchangeValue;
    }
}
