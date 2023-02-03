public class MyUtils {
    private final boolean PRINT_NEWLINE = true;
    public void print(Object object) {
        System.out.print(object);
        if (PRINT_NEWLINE) {
            System.out.println();
        }
    }

    public void print(Object object, boolean overrideNewline) {
        System.out.print(object);
        if (overrideNewline) {
            System.out.println();
        }
    }
}
