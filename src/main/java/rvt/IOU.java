import java.util.HashMap;
 
// IOU klase glaba paradu summas katrai personai
public class IOU {
    private HashMap<String, Double> hashmap;
 
    public IOU() {
        this.hashmap = new HashMap<>();
    }
 
    // Saglaba vai parraksta paradu summu konkretai personai
    public void setSum(String toWhom, double amount) {
        this.hashmap.put(toWhom, amount);
    }
 
    // Atgriez paradu summu konkretai personai, vai 0.0 ja nav atrasta
    public double howMuchDoIOweTo(String toWhom) {
        return this.hashmap.getOrDefault(toWhom, 0.0);
    }
}
 
class IOUMain {
    public static void main(String[] args) {
        IOU mattsIOU = new IOU();
        mattsIOU.setSum("Arthur", 51.5);
        mattsIOU.setSum("Michael", 30);
 
        System.out.println(mattsIOU.howMuchDoIOweTo("Arthur"));
        System.out.println(mattsIOU.howMuchDoIOweTo("Michael"));
    }
}