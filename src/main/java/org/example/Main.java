package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger lengthThree = new AtomicInteger(0);
    public static AtomicInteger lengthFour = new AtomicInteger(0);
    public static AtomicInteger lengthFive = new AtomicInteger(0);

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String nickName) {
        return nickName.equalsIgnoreCase(new StringBuilder(nickName).reverse().toString());
    }

    public static boolean isSameLetterWord(String nickName) {
        for (int i = 0; i < nickName.length() - 1; i++) {
            if (nickName.charAt(i) == nickName.charAt(i + 1)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isSortString(String nickName) {
        for (int i = 0; i < nickName.length() - 1; i++) {
            if (nickName.charAt(i) <= nickName.charAt(i + 1)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    
    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        new Thread(() -> {
            for (String nick : texts) {
                if (isPalindrome(nick)) {
                    switch (nick.length()) {
                        case 3:
                            lengthThree.incrementAndGet();
                            break;
                        case 4:
                            lengthFour.incrementAndGet();
                            break;
                        case 5:
                            lengthFive.incrementAndGet();
                            break;
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (String nick : texts) {
                if (isSameLetterWord(nick)) {
                    switch (nick.length()) {
                        case 3:
                            lengthThree.incrementAndGet();
                            break;
                        case 4:
                            lengthFour.incrementAndGet();
                            break;
                        case 5:
                            lengthFive.incrementAndGet();
                            break;
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (String nick : texts) {
                if (isSortString(nick)) {
                    switch (nick.length()) {
                        case 3:
                            lengthThree.incrementAndGet();
                            break;
                        case 4:
                            lengthFour.incrementAndGet();
                            break;
                        case 5:
                            lengthFive.incrementAndGet();
                            break;
                    }
                }
            }
        }).start();

        System.out.println("Красивых слов с длиной 3: " + lengthThree.get() + " шт");
        System.out.println("Красивых слов с длиной 4: " + lengthFour.get() + " шт");
        System.out.println("Красивых слов с длиной 5: " + lengthFive.get() + " шт");
    }

}