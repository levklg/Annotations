import myjunit.After;
import myjunit.Before;
import myjunit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BasketTest {

    HashMap <String, Integer> basketMap = new HashMap<>();
    Basket basket;

    @Before
    void testBefore(){
        basket = new Basket(basketMap);
        System.out.println("Befor_test " + this.hashCode());
    }

    @Test
    void test1(){
        basket.add("apple",3);
        System.out.println("test1 " + this.hashCode());
    }
    @Test
    void test2(){
        basket.add("pear",4);
        System.out.println("test2 " + this.hashCode());
    }

    @Test
    void test3(){
        basket.add("orange",2);
        System.out.println("test3 " + this.hashCode());
    }
    @After
    void  testAfter(){
        basket.showBasket();
        System.out.println("After" + this.hashCode());
    }

    @After
    void  testAfter2(){
        basket.cleanBasket();
        System.out.println("After2 " + this.hashCode());
    }




}
