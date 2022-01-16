package edu.cmu.cs214.hw3;

import java.io.IOException;

import edu.cmu.cs214.hw3.service.GameHandler;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

public class App extends RouterNanoHTTPD{
    public App() throws IOException {
        super(8080);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    public static void main(String[] args) throws IOException {
        new App();        
    }

    @Override
    public void addMappings() {
        addRoute("/", IndexHandler.class);
        addRoute("/test", GameHandler.class);
    }


    @Override
    public Response serve(IHTTPSession session) {
        return newFixedLengthResponse("Hello world");
    }
    
}
