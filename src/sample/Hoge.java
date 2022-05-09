package sample;

public class Hoge {

    public static void main(String[] args) {

        String s1 = getBanana();
        System.out.println(s1 + "が食べたい");

        String s2 = getStrawberry();
        System.out.println(s2 + "が食べたい");
    }

    // プライベート関数
    private static String getBanana() {
        return "バナナ";
    }

    // プライベート関数
    private static String getStrawberry() {
        return "イチゴ";
    }
}