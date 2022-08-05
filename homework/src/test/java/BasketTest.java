
import myjunit.After;
import myjunit.Before;
import myjunit.Test;

import java.util.HashMap;


public class BasketTest {
    HashMap <String, Integer> basketMap = new HashMap<>();
    Basket basket;

   @Before
    void test(){
      basket = new Basket(basketMap);
       System.out.println("Befor_test " + this.hashCode());
    }

  @Test
    void test1(){
       basket.add("apple",3);
      System.out.println("Test_test1 " + this.hashCode());
    }

   @After
    void  test2(){
        basket.showBasket();
       System.out.println("After_test2 " + this.hashCode());
    }




    @Before
    void testBefor(){
        System.out.println("Befor " + this.hashCode());
    }

    @Test
    void TestTest(){
        System.out.println("Test " + this.hashCode());
    }

    @After
        void TestAfter(){
         System.out.println("After " + this.hashCode());
    }



}
