package uk.ac.ebi.intact.style.mapper.ontology.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Component
public class ProxyManager {

    private static final Log log = LogFactory.getLog(TaxonMapper.class);

    private static final String HTTPS_PROXY_HOST = "HTTPS_PROXY_HOST";// kubernetes
    private static final String HTTPS_PROXY_PORT = "HTTPS_PROXY_PORT";// kubernetes
    private static final String HTTP_PROXY_HOST = "HTTP_PROXY_HOST";// hinxton machine
    private static final String HTTP_PROXY_PORT = "HTTP_PROXY_PORT";//hinxton machine
    private static Proxy proxy = Proxy.NO_PROXY;

    @Autowired
    private ProxyManager(Environment env) {

        String proxyHost = null;
        String proxyPort = null;

        if (env.getProperty(HTTPS_PROXY_HOST) != null && env.getProperty(HTTPS_PROXY_PORT) != null) {
            proxyHost = env.getProperty(HTTPS_PROXY_HOST);
            proxyPort = env.getProperty(HTTPS_PROXY_PORT);
        } else if (env.getProperty(HTTP_PROXY_HOST) != null && env.getProperty(HTTP_PROXY_PORT) != null) {
            proxyHost = env.getProperty(HTTP_PROXY_HOST);
            proxyPort = env.getProperty(HTTP_PROXY_PORT);
        }

        if (proxyHost != null && proxyPort != null) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort)));
        }
    }

    public Proxy getProxy() {
        if (proxy.address() != null) {
            log.info("Proxy address:" + proxy.address().toString());
        }
        return proxy;
    }

}