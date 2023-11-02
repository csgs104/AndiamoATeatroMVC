package org.example;

import org.example.controllers.UtenteController;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UtenteController utenteController = new UtenteController();
        utenteController.startServices();
    }
}
