package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Starting vertex deployments" );
        Vertex vertex = vertex.vertex();

        vertex.deployVerticle(EventBusServiceVerticle);
        vertex.deployVerticle(HttpServiceVerticle);
        
    }
}
