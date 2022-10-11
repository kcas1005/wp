package com.fastcampus.java.repository;

import com.fastcampus.java.model.entity.Item;
import com.fastcampus.java.model.entity.OrderDetail;
import com.fastcampus.java.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
//@DisplayName("ItemRepositoryTest 테스트")
@SpringBootTest
public class AllTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;


    //user test

    @Test
    public void create(){
        // String sql = insert into user (%s, %s , %d ) value (account, email, age);
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("newUser : "+newUser);

    }

    @Test
    //@Transactional
    public void read(){

        // select * from user where id = ?
        Optional<User> user = userRepository.findByAccountAndEmail("TestUser03","TestUser03@gmail.com");
        System.out.println(user);
        user.ifPresent(selectUser ->{
            System.out.println(selectUser);
            selectUser.getOrderDetailList().stream().forEach(detail ->{
                Item item = detail.getItem();
                System.out.println(item);
            });

        });
    }

    @Test
//    @Transactional
    public void update(){

        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser ->{
           // selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    //@Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

//        Assertions.assertTrue(user.isPresent());    // false


        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);
        System.out.println(deleteUser);

//        Assertions.assertFalse(deleteUser.isPresent()); // false
    }

//item insert,select

    @Test
    public void create2(){//2개입력

        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("삼성 노트북");

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
        System.out.println(newItem);
    }

    @Test
    public void read2(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);
        Assertions.assertTrue(item.isPresent());
        System.out.println(item);
    }


    //orderDetail insert
    @Test
    public void create3(){
        // String sql = insert into user (%s, %s , %d ) value (account, email, age);
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("newUser : "+newUser);
    }

    //orderDetail
    @Test
    public void create4(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderAt(LocalDateTime.now());
        // 어떤사람?
        orderDetail.setUser(userRepository.findById(1l).get());
        // 어떤 상품?
        orderDetail.setItem(itemRepository.findById(1l).get());
        //orderDetail.setItemId(1L);
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
    }

    @Test//다음은 안돌아감
    public void create5(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderAt(LocalDateTime.now());


        User user = new User();
        //user.setId(6l);  있거나 없거나 db에 없으면 안돌아간다.
        user.setAccount("TestUser05");
        user.setEmail("TestUser05@gmail.com");
        user.setPhoneNumber("010-1111-5555");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser5");

        // 어떤사람?
        orderDetail.setUser(user);//자동저장되지 않음
        // 어떤 상품?
        orderDetail.setItem(itemRepository.findById(1l).get());
        //orderDetail.setItemId(1L);
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
    }

    @Test//2l user에 1l과 2l를 넣는다.
    public void create6(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderAt(LocalDateTime.now());
        // 어떤사람?
        orderDetail.setUser(userRepository.findById(6l).get());
        // 어떤 상품?
        orderDetail.setItem(itemRepository.findById(1l).get());
        //orderDetail.setItemId(1L);
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
    }

    @Test//
    public void select6(){
        Optional<OrderDetail> orderDetail=orderDetailRepository.findById(3l);

        orderDetail.ifPresent(oneData ->{
                    System.out.println(oneData);
                    System.out.println(oneData.getId());
                    System.out.println(oneData.getUser());
                    System.out.println(oneData.getItem());
                });

    }
    @Test//
    //@Transactional
    public void read5(){

        // select * from user where id = ?
        Optional<User> user = userRepository.findByAccountAndEmail("TestUser03","TestUser03@gmail.com");
        System.out.println(user);
        user.ifPresent(selectUser ->{
            System.out.println(selectUser);
            selectUser.getOrderDetailList().stream().forEach(detail ->{
                Item item = detail.getItem();
                System.out.println(item);
            });

        });
    }

    public void delete5(){
        // select * from user where id = ?
        Optional<User> user = userRepository.findByAccountAndEmail("TestUser03","TestUser03@gmail.com");
        System.out.println(user);
        user.ifPresent(selectUser ->{
            System.out.println(selectUser);
            selectUser.getOrderDetailList().stream().forEach(detail ->{
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
    }


    @Test
    //@Transactional
    public void delete4(){
        Optional<User> user = userRepository.findById(2L);
        Assertions.assertTrue(user.isPresent());    // false
        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });
        Optional<User> deleteUser = userRepository.findById(2L);
        Assertions.assertFalse(deleteUser.isPresent()); // false
    }


//    user.ifPresent(selectUser ->{
//        System.out.println(selectUser);
//        selectUser.getOrderDetailList().stream().forEach(detail ->{
//            Item item = detail.getItem();
//            System.out.println(item);
//        });
//
//    });

    @Test
    //@Transactional
    public void delete6(){
        Optional<User> user = userRepository.findById(5L);
        System.out.println(user);
       // Assertions.assertTrue(user.isPresent());    // false
        user.ifPresent(selectUser->{
            selectUser.getOrderDetailList().stream().forEach(detail ->{
                orderDetailRepository.delete(detail);
                System.out.println(detail);
            });
        });
        userRepository.delete( userRepository.findById(5L).get());

//        Optional<User> deleteUser = userRepository.findById(3L);
//        Assertions.assertFalse(deleteUser.isPresent()); // false
//        System.out.println(user);
    }

    //여러개의 데이터를 읽어서 처리하려면     List를 사용하자.

//여러개 데이터 처리 방법
//    @Repository
//    public interface PartnerRepository extends JpaRepository<Partner, Long> {
//        List<Partner> findByCategory(Category category);
//    }

//    @Test
//    public void createHomeAppliance(){
//        String type = "COMPUTER";
//        Category category = categoryRepository.findByType(type).get();
//        List<Partner> partnerList = partnerRepository.findByCategory(category);
//
//        for(Partner p : partnerList){
//
//            for(int i = 1 ; i < 6; i ++){
//                int div = (random.nextInt(10)+1) % 2;
//                ItemStatus status = (div == 0 ? ItemStatus.REGISTERED : ItemStatus.UNREGISTERED);
//
//                Item item = Item.builder()
//                        .partner(p)
//                        .status(status)
//                        .name(category.getTitle()+i+"호")
//                        .title(p.getName()+"의 가전제품"+i+"호")
//                        .price(BigDecimal.valueOf((long)random.nextInt(999999)+1))
//                        .content(p.getName()+"의 가전제품"+i+"호"+"의 가전제품 입니다. 2019년도 신제품 입니다")
//                        .brandName(p.getName())
//                        .registeredAt(getRandomDate())
//                        .unregisteredAt(status.equals(ItemStatus.UNREGISTERED) ? getRandomDate() : null )
//                        .build();
//
//                itemRepository.save(item);
//            }
//        }
//    }

    @Test
//    @Transactional
public void update2(){
    Optional<User> user = userRepository.findByAccountAndEmail("TestUser03","TestUser03@gmail.com");
    System.out.println(user);
    user.ifPresent(selectUser ->{
        System.out.println(selectUser);
        selectUser.setCreatedAt(LocalDateTime.now());
        selectUser.getOrderDetailList().stream().forEach(detail ->{
           detail.setOrderAt(null);
            System.out.println(detail);
        });
    });
    userRepository.save( user.get());
        //하위에 update는 일일이 해줘야 한다.
        //일관성이 없으면 동작하지 않는다.


//        user.ifPresent(selectUser ->{
//            selectUser.setAccount("PPPP111");
//            selectUser.setUpdatedAt(LocalDateTime.now());
//            selectUser.setUpdatedBy("update method()");
//
//            userRepository.save(selectUser);
//        });
}

    @Test
//    @Transactional
    public void inser55() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderAt(LocalDateTime.now());
        // 어떤사람?
        User user=new User();
        user.setId(6l);
        orderDetail.setUser(user);
        // 어떤 상품?
        Item item=new Item();
        item.setId(1l);
        orderDetail.setItem(item);
        //orderDetail.setItemId(1L);
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
        //들어가지만 item과 user가 변경되지는 않는다.
        //있는데이터만 들어간다.
    }

}
