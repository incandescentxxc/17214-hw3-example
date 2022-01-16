
package edu.cmu.cs214.hw3;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cmu.cs214.hw3.service.GameService;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        final Server server = newServer(8080);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop().join();
            logger.info("Server has stopped.");
        }));
        server.start().join();
        logger.info("Server has been running at http://localhost:{}", server.activeLocalPort());
    }

    static Server newServer(int port) {
        final ServerBuilder sb = Server.builder();
        return sb.http(port).annotatedService(new GameService()).build();
    }
}
