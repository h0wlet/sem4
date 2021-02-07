package ccfit.nsu.ru;

public class Main {

    public static void main(String[] args) {
        wordCount obj1 = new wordCount();
        String inputFile = "input.txt";
        String outputFile = "output.csv";
        try {
            obj1.readAndCount(inputFile);
            obj1.sortAndWrite(outputFile);
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
