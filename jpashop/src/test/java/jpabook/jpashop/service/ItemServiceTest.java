package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired ItemRepository itemRepository;
    @Autowired ItemService itemService;

    @Test
    public void 신규등록() throws Exception{
        //given
        Item item1 = new Book();
        itemService.saveItem(item1);

        //when
        itemService.saveItem(item1);
        Long item1Id = item1.getId();

        //then
        assertEquals(item1,itemRepository.findOne(item1Id));

    }

    @Test
    public void 추가등록(){

    }

    @Test
    public void 상품검색(){

    }

}