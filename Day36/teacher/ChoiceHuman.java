public class ChoiceHuman implment eat {

    private String name;
    private String jellyName;

    ChoiceHuman() {
        this.name = "알 수 없는";
    }

    ChoiceHuman(String input_name, String input_jelly) {
        this.name = input_name;
        this.jellyName = input_jelly
    }

    public void eat(String action) {
        System.out.println(this.name + "이/(가) " + this.jellyName "을/(를) " +action);
    }

}

