import myjunit.After;
import myjunit.Before;
import myjunit.Test;

import java.util.HashMap;


public class BasketTest {
    HashMap <String, Integer> basketMap;
    Basket basket;

    @Before
    void testBefor(){
        basketMap = new HashMap<>();
        basket = new Basket(basketMap);
        System.out.println("Befor_test " + this.hashCode());
    }

    @Test
    void test1(){
        basket.add("apple",3);
        System.out.println("Test_test1 " + this.hashCode());
    }


    @Test
    void test2(){
        basket.cleanBasket();
    }

    @After
    void  testAfter(){
        basket.showBasket();
        System.out.println("After_test2 " + this.hashCode());
    }




}
