package Controllers;

import java.security.SecureRandom;
import java.util.Random;

public class KeyManager {

    private final Random Random = new SecureRandom();
    private final String Alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private String key;
    private int length= getRandomIntegerBetweenRange(30.0,35.0);

    public KeyManager () throws Exception{
        key = enableKey(length);
    }

    private String enableKey (int length){
        StringBuilder returnValue = new StringBuilder(length);

        for (int i=0; i <length;i++){
            returnValue.append(Alphabet.charAt(Random.nextInt(Alphabet.length())));
        }
        return new String(returnValue);
    }
    private int getRandomIntegerBetweenRange (double min, double max){
        int x = (int)((Math.random()*((max-min)+1)) +min);
        return x;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
