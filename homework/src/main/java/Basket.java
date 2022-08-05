import java.util.HashMap;
import java.util.Map;

public class Basket {
    public  Map<String,Integer> basketMap ;

    public Basket(){
        this.basketMap = new HashMap<>();
    }

    public Basket(Map<String, Integer> basketMap) {
        this.basketMap = basketMap;
    }


    public  void add(String name, Integer quantity){
        if (basketMap.containsKey(name))
        {
         var value = basketMap.get(name);
         quantity = value + quantity;
         this.basketMap.put(name, quantity);
        }else this.basketMap.put(name, quantity);

    }

    public  void delete(String name, Integer quantity){
        if (basketMap.containsKey(name))
        {
            var value = basketMap.get(name);
           if(value < quantity || value <= 0) {
               this.basketMap.put(name, 0);
           }else {
               quantity = value - quantity;
               this.basketMap.put(name, quantity);
           }
        }

    }



    public  void cleanBasket(){
        this.basketMap.clear();
    }

    public  void showBasket(){
        if(this.basketMap == null || this.basketMap.isEmpty()) System.out.println(" Карзина пуста");
        else {
            for (Map.Entry<String, Integer> entry : basketMap.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }


    protected boolean canEqual(final Object other) {
        return other instanceof Basket;
    }

    public Map<String, Integer> getBasketMap() {
        return this.basketMap;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Basket)) return false;
        final Basket other = (Basket) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$basketMap = this.getBasketMap();
        final Object other$basketMap = other.getBasketMap();
        if (this$basketMap == null ? other$basketMap != null : !this$basketMap.equals(other$basketMap)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $basketMap = this.getBasketMap();
        result = result * PRIME + ($basketMap == null ? 43 : $basketMap.hashCode());
        return result;
    }

    public String toString() {
        return "Basket(basketMap=" + this.getBasketMap() + ")";
    }
}
