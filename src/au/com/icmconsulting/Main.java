package au.com.icmconsulting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        int countPlayer1 = 0;
        int countPlayer2 = 0;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = null;
            try {
                line = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            Set<String> cardSet = new LinkedHashSet<>(Arrays.asList(line.split("\\s+")));
            boolean valid = true;
            if (cardSet.size() != Const.CARDS_REQUIRED) {
                valid = false;
            } else {
                for (String c : cardSet) {
                    if (!c.matches("[2-9TJQKA][CSHD]")) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                System.out.println(MessageFormat.format("Dirty data: {0}", line));
            } else {
                PokerHand pokerHandPlayer1 = new PokerHand(cardSet.stream()
                        .limit(Const.CARDS_REQUIRED / 2)
                        .collect(Collectors.toSet())
                );
                PokerHand pokerHandPlayer2 = new PokerHand(cardSet.stream()
                        .skip(Const.CARDS_REQUIRED / 2)
                        .limit(Const.CARDS_REQUIRED / 2)
                        .collect(Collectors.toSet())
                );
                int result = pokerHandPlayer1.beat(pokerHandPlayer2);
                if (result > 0) {
                    countPlayer1++;
                } else if (result == 0) {
                    countPlayer2++;
                }
            }
        }

        System.out.println(MessageFormat.format("Player 1: {0} hands\nPlayer 2: {1} hands",
                countPlayer1, countPlayer2));
    }
}
