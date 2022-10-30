package nextstep.ladder.view;

import nextstep.ladder.domain.HorizontalLine;
import nextstep.ladder.domain.Person;

import java.util.List;
import java.util.stream.Collectors;

import static nextstep.ladder.util.StringUtil.appendWhiteSpaceInside;

public class Output {
    private final static String ENTER = System.lineSeparator();
    private final static String GAME_RESULT = "실행 결과";
    private final static String MARK_HORIZONTAL_LINE = "-----";
    private final static String MARK_VERTICAL_LINE = "|";
    private final static String WHITE_SPACE = "";

    private static StringBuilder print = new StringBuilder(GAME_RESULT).append(ENTER);

    public static void printLadder(List<Person> people, List<HorizontalLine> horizontalLines) {
        printNames(people);
        printLines(horizontalLines);
        System.out.println(print.toString());
    }

    private static void printNames(List<Person> people) {
        people.stream()
                .map(person -> appendWhiteSpaceInside(person.name(), 6))
                .forEach(print::append);
        print.append(ENTER);
    }

    private static void printLines(List<HorizontalLine> horizontalLines) {
        horizontalLines.stream()
                .map(horizontalLine -> parsedPoints(horizontalLine))
                .forEach(horizontalLine -> {
                    print.append(parsedVerticalLine(horizontalLine));
                    print.append(ENTER);
                });
    }

    private static List<String> parsedPoints(HorizontalLine horizontalLine) {
        return horizontalLine.points()
                .stream()
                .map(point -> point.left() == true ? MARK_HORIZONTAL_LINE : appendWhiteSpaceInside(WHITE_SPACE, 5))
                .collect(Collectors.toList());
    }

    private static String parsedVerticalLine(List<String> horizontalLine) {
        return String.join(MARK_VERTICAL_LINE, horizontalLine)
                .concat(MARK_VERTICAL_LINE);
    }
}
