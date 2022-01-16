package edu.cmu.cs214.hw3.service;

import fi.iki.elonen.NanoHTTPD.Response;
import fi.iki.elonen.router.RouterNanoHTTPD.DefaultHandler;

public class GameHandler extends DefaultHandler {
    @Override
    public String getText() {
        return "UserA, UserB, UserC";
    }

    @Override
    public String getMimeType() {
        return "string";
    }

    @Override
    public Response.IStatus getStatus() {
        return Response.Status.OK;
    }
}
