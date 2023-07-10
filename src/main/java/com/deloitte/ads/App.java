package com.deloitte.ads;

import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        User mario = new User(1, "Mario", "Mariowski", new HashSet<>(), new HashSet<>());
        User luigi = new User(2, "Luigi", "Luigiowski", new HashSet<>(), new HashSet<>());

        Marios marios = new Marios(1, Marios.TypeEnum.HAPPY, "Hello", mario.getId(), luigi.getId());

        mario.addSentMarios(marios.getId());
        luigi.addReceivedMarios(marios.getId());

    }
}
