package com.nibl.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Connector;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationConfiguration {

    private static Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);

    public class EmbeddedTomcatConfiguration {

        @Value("${server.additionalPorts}")
        private String additionalPorts;

        @Value("${server.ssl.key-store}")
        private String absoluteKeyStore;

        @Value("${server.ssl.key-store-password}")
        private String keystorePass;

        @Value("${server.ssl.keyStoreType}")
        private String keystoreType;

        @Value("${server.ssl.keyAlias}")
        private String keyAlias;

        @Bean
        public EmbeddedServletContainerFactory servletContainer() {
            TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
            Connector[] additionalConnectors = this.additionalConnector();
            if (additionalConnectors != null && additionalConnectors.length > 0) {
                tomcat.addAdditionalTomcatConnectors(additionalConnectors);
            }
            return tomcat;
        }

        private Connector[] additionalConnector() {
            if (StringUtils.isBlank(this.additionalPorts)) {
                return null;
            }
            String[] ports = this.additionalPorts.split(",");
            List<Connector> result = new ArrayList<>();
            for (String port : ports) {
                Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
                Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();

                connector.setScheme("https");
                connector.setPort(Integer.valueOf(port));
                connector.setSecure(true);

                protocol.setSSLEnabled(true);
                protocol.setKeystoreFile(absoluteKeyStore);
                protocol.setKeystorePass(keystorePass);
                protocol.setKeystoreType(keystoreType);
                protocol.setKeyAlias(keyAlias);

                result.add(connector);
            }
            return result.toArray(new Connector[] {});
        }
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                log.info("Enabling CORS");
                // TODO Restrict this to the specific host and port of the UI app on localhost
                registry.addMapping("/**").allowedMethods("PUT", "POST", "GET").allowedHeaders("Content-Type"); // .allowedOrigins("http://localhost:8080");
            }
        };
    }
}
