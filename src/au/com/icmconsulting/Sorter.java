package au.com.icmconsulting;

import java.util.*;
import java.util.stream.Collectors;

public final class Sorter {
    public static int getHighest(boolean[][] cards) {
        int highest = -1;
        outer : for (int i = Const.VALUES.size() - 1; i >= 0; i--) {
            for (int j = 0; j < Const.SUITS.size(); j++) {
                if (cards[i][j]) {
                    highest = i;
                    break outer;
                }
            }
        }
        return highest;
    }

    public static boolean isFlush(boolean[][] cards) {
        int count = 0;
        for (int i = 0; i < Const.SUITS.size(); i++) {
            for (int j = 0; j < Const.VALUES.size(); j++) {
                if (cards[j][i]) count++;
            }
            if (count == 5) {
                return true;
            } else {
                count = 0;
            }
        }
        return false;
    }

    public static boolean isStraight(boolean[][] cards) {
        int count = 0;
        int lastIndex = -1;
        for (int i = 0; i < Const.VALUES.size(); i++) {
            boolean has = false;
            for (int j = 0; j < Const.SUITS.size(); j++) {
                if (cards[i][j]) {
                    has = true;
                    break;
                }
            }
            if (has) {
                if (count == 0) {
                    lastIndex = i;
                    count++;
                } else {
                    if (lastIndex == i - 1) {
                        lastIndex = i;
                        count++;
                    } else {
                        count = 0;
                    }
                }
            }
        }
        return count == 5;
    }

    public static boolean isStraightFlush(boolean[][] cards) {
        int count = 0;
        for (int i = 0; i < Const.SUITS.size(); i++) {
            int lastIndex = -1;
            for (int j = 0; j < Const.VALUES.size(); j++) {
                if (cards[j][i]) {
                    if (count == 0) {
                        lastIndex = j;
                        count++;
                    } else {
                        if (lastIndex == j - 1) {
                            lastIndex = j;
                            count++;
                        } else {
                            count = 0;
                        }
                    }
                }
            }
        }
        return count == 5;
    }

    public static boolean isRoyalFlush(boolean[][] cards) {
        return isStraightFlush(cards) && getHighest(cards) == Const.VALUES.size() - 1;
    }

    public static boolean isFourofaKind(boolean[][] cards) {
        int count = 0;
        for (int i = 0; i < Const.VALUES.size(); i++) {
            for (int j = 0; j < Const.SUITS.size(); j++) {
                if (cards[i][j]) count++;
            }
            if (count == 4) {
                return true;
            } else {
                count = 0;
            }
        }
        return false;
    }

    public static boolean isThreeofaKind(boolean[][] cards) {
        return getThreeofaKind(cards) >= 0;
    }


    public static int getThreeofaKind(boolean[][] cards) {
        int count = 0;
        for (int i = 0; i < Const.VALUES.size(); i++) {
            for (int j = 0; j < Const.SUITS.size(); j++) {
                if (cards[i][j]) count++;
            }
            if (count == 3) {
                return i;
            } else {
                count = 0;
            }
        }
        return -1;
    }

    public static boolean isFullHouse(boolean[][] cards) {
        int count = 0;
        for (int i = 0; i < Const.VALUES.size(); i++) {
            for (int j = 0; j < Const.SUITS.size(); j++) {
                if (cards[i][j]) count++;
            }
            if (count == 2) {
                break;
            } else {
                count = 0;
            }
        }
        return count == 2 && isThreeofaKind(cards);
    }

    public static boolean isTwoPairs(boolean[][] cards) {
        return getPairs(cards).size() == 2;
    }

    public static boolean isPair(boolean[][] cards) {
        return getPairs(cards).size() == 1;
    }

    public static List<Integer> getPairs(boolean[][] cards) {
        Map<Integer,Integer> pairs = new TreeMap<>();
        for (int i = 0; i < Const.VALUES.size(); i++) {
            for (int j = 0; j < Const.SUITS.size(); j++) {
                if (cards[i][j]) pairs.put(i, pairs.getOrDefault(i, 0) + 1);
            }
        }
        pairs.values().removeAll(Collections.singleton(1));
        return pairs.keySet().stream().collect(Collectors.toList());
    }
}
