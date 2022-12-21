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
        System.out.println("Befor_1 " );
    }

    @Test
    void test(){
        basket.add("apple",3);
        System.out.println("test_1 " );
    }

    @After
    void  testAfter(){
        basket.showBasket();
        System.out.println("After_1");
    }

    @Before
    void testBefore2(){
        basket = new Basket(basketMap);
        System.out.println("Befor_2 ");
    }

    @Test
    void test2(){
        basket.add("apple",3);
        System.out.println("test_2 ");
    }

    @After
    void  testAfter2(){
        basket.showBasket();
        System.out.println("After_2");
    }






}
