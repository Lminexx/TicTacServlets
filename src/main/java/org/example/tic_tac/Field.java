package org.example.tic_tac;

import java.util.*;
import java.util.stream.Collectors;

public class Field {
    private final Map<Integer, Sign> field;
    private final List<List<Integer>> winPossibilities = new ArrayList<>(List.of(
            List.of(0, 4, 8),
            List.of(2, 4, 6),
            List.of(3, 4, 5),
            List.of(0, 1, 2),
            List.of(6, 7, 8),
            List.of(0, 3, 6),
            List.of(1, 4, 7),
            List.of(2, 5, 8)
    ));

    public void setField(Integer index, Sign sign) {
        field.put(index, sign);
    }

    public Field() {
        field = new HashMap<>();
        field.put(0, Sign.EMPTY);
        field.put(1, Sign.EMPTY);
        field.put(2, Sign.EMPTY);
        field.put(3, Sign.EMPTY);
        field.put(4, Sign.EMPTY);
        field.put(5, Sign.EMPTY);
        field.put(6, Sign.EMPTY);
        field.put(7, Sign.EMPTY);
        field.put(8, Sign.EMPTY);
    }

    public Map<Integer, Sign> getField() {
        return field;
    }


    public List<Sign> getFieldData() {
        return field.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
    public int getEmptyFieldIndex() {
        return field.entrySet().stream()
                .filter(e -> e.getValue() == Sign.EMPTY)
                .map(Map.Entry::getKey)
                .findFirst().orElse(-1);
    }

    public int getWinningPosition() {
        Collections.shuffle(winPossibilities);
        for (var win : winPossibilities) {
            int emptyCount = 0;
            List<Integer> array = new ArrayList<>();
            for (int index : win) {
                if (field.get(index) == Sign.EMPTY || field.get(index) == Sign.NOUGHT) {
                    if(field.get(index) == Sign.EMPTY) {
                        array.add(index);
                    }
                    emptyCount++;
                } else {
                    break;
                }
            }
            if (emptyCount == 3) {
                return array.get(0);
            }
        }
        return getEmptyFieldIndex();
    }
    public Sign checkWin() {
        for (List<Integer> winPossibility : winPossibilities) {
            if (field.get(winPossibility.get(0)) == field.get(winPossibility.get(1))
                    && field.get(winPossibility.get(0)) == field.get(winPossibility.get(2))) {
                return field.get(winPossibility.get(0));
            }
        }
        return Sign.EMPTY;
    }
}