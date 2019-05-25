package com.pw.elka.flighttickets.distributor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetSocketAddress;
import java.net.Proxy;

import static com.pw.elka.flighttickets.distributor.akka.cfg.SpringExtension.SPRING_EXTENSION_PROVIDER;
import static springfox.documentation.builders.PathSelectors.regex;

@PropertySource("classpath:application.properties")
@Configuration
@EnableSwagger2
public class Cfg {


    @Value("${http.proxyPort}")
    private Integer port;

    @Value("${http.proxyHost}")
    private String host;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ActorSystem actorSystem;


    @Bean
    public ActorSystem actorSystem() {
        String configFile = System.getProperty("config.file");
        Config config = ConfigFactory.load(configFile);
        ActorSystem system = ActorSystem.create("akka-spring-demo", config);
        System.out.println(system.settings().config().getString("akka.remote.netty.tcp.port"));
        SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);
        return system;
    }

    @Bean
    public ActorRef createGreeter() {
        return actorSystem.actorOf(SPRING_EXTENSION_PROVIDER.get(actorSystem).props("bookingActor"), "greeter");
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.pw.elka"))
                .paths(regex("/*.*"))
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
        requestFactory.setProxy(proxy);

        return new RestTemplate(requestFactory);
    }
}
