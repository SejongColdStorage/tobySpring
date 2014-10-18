package learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public int calcSum(String filePath) throws IOException {
        LineCallback sumCallback = (line, value) -> value + Integer.valueOf(line);
        return lineReadTemplate(filePath, sumCallback, 0);
    }

    public int calcMultiply(String filePath) throws IOException {
        LineCallback sumCallback = (line, value) -> value * Integer.valueOf(line);
        return lineReadTemplate(filePath, sumCallback, 1);
    }


    private int lineReadTemplate(String filePath, LineCallback callback, int initVal) throws IOException{
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            Integer res = initVal;
            String line = null;
            int ret = callback.doSomethingWithLine(line, res);

            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {

                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
