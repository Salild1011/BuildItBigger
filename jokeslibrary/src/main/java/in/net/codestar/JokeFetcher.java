package in.net.codestar;

import java.util.Random;

public class JokeFetcher {

    private static final String[] JOKES_ARR = {
            "Two bytes meet. The first byte asks, \"Are you ill?\"" +
                    "The second byte replies, \"No, just feeling a bit off.\"",
            "Why do programmers always mix up Halloween and Christmas? " +
                    "Because Oct 31 equals Dec 25.",
            "How many programmers does it take to change a light bulb? " +
                    "None-It\'s a hardware problem"
    };

    public String getJoke() {
        Random random = new Random();
        return JOKES_ARR[random.nextInt(JOKES_ARR.length)];
    }
}

