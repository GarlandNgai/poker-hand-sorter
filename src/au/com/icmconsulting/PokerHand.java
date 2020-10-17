package au.com.icmconsulting;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PokerHand {
    private boolean[][] cards;

    public PokerHand(Set<String> cardSet) {
        cards = new boolean[Const.VALUES.size()][Const.SUITS.size()];
        for (String c : cardSet) {
            cards[Const.VALUES.get(String.valueOf(c.charAt(0)))][Const.SUITS.get(String.valueOf(c.charAt(1)))] = true;
        }
    }

    public boolean[][] getCards() {
        return cards;
    }

    public int beat (PokerHand opponent) {
        boolean[][] opponentCards = opponent.getCards();

        if (Sorter.isRoyalFlush(cards)) {
            if (!Sorter.isRoyalFlush(opponentCards)) {
                return 1;
            }
        } else {
            if (Sorter.isRoyalFlush(opponentCards)) {
                return 0;
            }
        }

        if (Sorter.isStraightFlush(cards)) {
            if (!Sorter.isStraightFlush(opponentCards)) {
                return 1;
            }
        } else {
            if (Sorter.isStraightFlush(opponentCards)) {
                return 0;
            }
        }

        if (Sorter.isFourofaKind(cards)) {
            if (!Sorter.isFourofaKind(opponentCards)) {
                return 1;
            }
        } else {
            if (Sorter.isFourofaKind(opponentCards)) {
                return 0;
            }
        }

        if (Sorter.isFullHouse(cards)) {
            if (!Sorter.isFullHouse(opponentCards)) {
                return 1;
            }
        } else {
            if (Sorter.isFullHouse(opponentCards)) {
                return 0;
            }
        }

        if (Sorter.isFlush(cards)) {
            if (!Sorter.isFlush(opponentCards)) {
                return 1;
            }
        } else {
            if (Sorter.isFlush(opponentCards)) {
                return 0;
            }
        }

        if (Sorter.isStraight(cards)) {
            if (!Sorter.isStraight(opponentCards)) {
                return 1;
            }
        } else {
            if (Sorter.isStraight(opponentCards)) {
                return 0;
            }
        }

        if (Sorter.getThreeofaKind(cards) > Sorter.getThreeofaKind(opponentCards)) {
            return 1;
        } else if (Sorter.getThreeofaKind(cards) < Sorter.getThreeofaKind(opponentCards)) {
            return 0;
        }

        if (Sorter.isTwoPairs(cards)) {
            if (!Sorter.isTwoPairs(opponentCards)) {
                return 1;
            } else {
                if (Sorter.getPairs(cards).get(1) > Sorter.getPairs(opponentCards).get(1)) {
                    return 1;
                } else if (Sorter.getPairs(cards).get(1) < Sorter.getPairs(opponentCards).get(1)) {
                    return 0;
                } else if (Sorter.getPairs(cards).get(0) > Sorter.getPairs(opponentCards).get(0)) {
                    return 1;
                } else if (Sorter.getPairs(cards).get(0) < Sorter.getPairs(opponentCards).get(0)) {
                    return 0;
                }
            }
        } else {
            if (Sorter.isTwoPairs(opponentCards)) {
                return 0;
            }
        }

        if (Sorter.isPair(cards)) {
            if (!Sorter.isPair(opponentCards)) {
                return 1;
            } else {
                if (Sorter.getPairs(cards).get(0) > Sorter.getPairs(opponentCards).get(0)) {
                    return 1;
                } else if (Sorter.getPairs(cards).get(0) < Sorter.getPairs(opponentCards).get(0)) {
                    return 0;
                }
            }
        } else {
            if (Sorter.isPair(opponentCards)) {
                return 0;
            }
        }

        for (int i = Const.VALUES.size() - 1; i >= 0; i--) {
            boolean has = false;
            boolean opponentHas = false;
            for (int j = 0; j < Const.SUITS.size(); j++) {
                if (cards[i][j]) {
                    has = true;
                }
                if (opponentCards[i][j]) {
                    opponentHas = true;
                }
            }
            if (has) {
                if (!opponentHas) {
                    return 1;
                }
            } else {
                if (opponentHas) {
                    return 0;
                }
            }
        }

        System.out.println(MessageFormat.format("Tie: {0} vs {1}",
                Arrays.stream(cards)
                    .map(Arrays::toString)
                    .collect(Collectors.joining()),
                Arrays.stream(opponentCards)
                    .map(Arrays::toString)
                    .collect(Collectors.joining())));
        return -1;
    }

}
