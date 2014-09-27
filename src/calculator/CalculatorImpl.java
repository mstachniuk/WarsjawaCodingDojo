package calculator;

import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculatorImpl implements Calculator {
    @Override
    public int add(String args) {
        if (!args.isEmpty()) {
            List<String> delimiters = Stream.of(",").collect(Collectors.<String>toList());


            if (args.startsWith("//")) {

                if (args.startsWith("//[")) {
                    delimiters.add(args.substring(3, args.indexOf("]")));
                } else {
                    delimiters.add(args.substring(2, args.indexOf("\n")));
                }
                args = args.substring(args.indexOf("\n") + 1);
            }

            String regexp = "(";

            StringJoiner stringJoiner = new StringJoiner("|");

            for (String delimiter : delimiters) {
                stringJoiner.add("(" + Pattern.quote(delimiter) + ")");
            }

            String[] split = args.split(stringJoiner.toString() + "|(\\n)", -1);

            Integer sum = 0;
            for (String s : split) {
                if(s.isEmpty()){
                    throw new IllegalArgumentException();
                }
                int argInt = Integer.parseInt(s);
                if (argInt < 0) {
                    throw new IllegalArgumentException();
                }
                else if (argInt <= 1000) {
                    sum += argInt;
                }

            }
            return sum;
        }
        return 0;
    }


}
