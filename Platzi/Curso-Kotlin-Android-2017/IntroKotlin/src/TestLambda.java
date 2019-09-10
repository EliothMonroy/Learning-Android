class TestLambda {
    public static void main(String[] args) {//Como en test pero usando lambda
        new Thread( ()->{System.out.println("New thread created");}).start();
    }
}
