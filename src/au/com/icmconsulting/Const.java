package au.com.icmconsulting;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Const {
    public static final int CARDS_REQUIRED = 10;
    public static final Map<String, Integer> VALUES = Stream.of(
            new AbstractMap.SimpleEntry<>("2", 0),
            new AbstractMap.SimpleEntry<>("3", 1),
            new AbstractMap.SimpleEntry<>("4", 2),
            new AbstractMap.SimpleEntry<>("5", 3),
            new AbstractMap.SimpleEntry<>("6", 4),
            new AbstractMap.SimpleEntry<>("7", 5),
            new AbstractMap.SimpleEntry<>("8", 6),
            new AbstractMap.SimpleEntry<>("9", 7),
            new AbstractMap.SimpleEntry<>("T", 8),
            new AbstractMap.SimpleEntry<>("J", 9),
            new AbstractMap.SimpleEntry<>("Q", 10),
            new AbstractMap.SimpleEntry<>("K", 11),
            new AbstractMap.SimpleEntry<>("A", 12))
            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    public static final Map<String, Integer> SUITS = Stream.of(
            new AbstractMap.SimpleEntry<>("C", 0),
            new AbstractMap.SimpleEntry<>("S", 1),
            new AbstractMap.SimpleEntry<>("H", 2),
            new AbstractMap.SimpleEntry<>("D", 3))
            .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
}
