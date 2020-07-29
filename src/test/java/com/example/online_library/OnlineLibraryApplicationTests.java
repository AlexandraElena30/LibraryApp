package com.example.online_library;

import com.example.online_library.domain.entity.ProductsEntity;
import com.example.online_library.domain.entity.UsersEntity;
import com.example.online_library.domain.model.ProductsDTO;
import com.example.online_library.domain.model.UsersDTO;
import com.example.online_library.mapper.ProductsEntityToProductsMapper;
import com.example.online_library.mapper.ProductsToProductsEntityMapper;
import com.example.online_library.mapper.UsersEntityToUsersMapper;
import com.example.online_library.mapper.UsersToUsersEntityMapper;
import com.example.online_library.repository.ProductsRepository;
import com.example.online_library.repository.UsersRepository;
import com.example.online_library.service.ProductsService;
import com.example.online_library.service.UsersService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OnlineLibraryApplicationTests {

    @Mock
    private ProductsRepository repositoryProducts;

    @Mock
    private ProductsEntityToProductsMapper productsEntityToProductsMapper;

    @Mock
    private ProductsToProductsEntityMapper productsToProductsEntityMapper;

    @Mock
    private UsersRepository repositoryUsers;

    @Mock
    private UsersEntityToUsersMapper usersEntityToUsersMapper;

    @Mock
    private UsersToUsersEntityMapper usersToUsersEntityMapper;

    @InjectMocks
    private UsersService sutUsers;

    @InjectMocks
    private ProductsService sutProducts;

    @Test
    public void given_input_when_register_then_userDTO_returned() {
        //GIVEN
        UsersDTO userDTO = UsersDTO.builder()
                .userID(110L)
                .lastName("Popescu")
                .firstName("Marin")
                .password("sdga32553")
                .email("popescu@gmail.com")
                .balance(BigDecimal.valueOf(0.0))
                .userType(1L)
                .bonusPoints(0L)
                .address(" Bd.Severin 156")
                .basket("")
                .phone("07243131")
                .build();

        var usersDTOMock = Mockito.mock(UsersDTO.class);
        var usersEntityMock = Mockito.mock(UsersEntity.class);
        var savedUsersMock = Mockito.mock(UsersEntity.class);

        BDDMockito.given(usersToUsersEntityMapper.convert(usersDTOMock)).willReturn(usersEntityMock);
        BDDMockito.given(repositoryUsers.save(usersEntityMock)).willReturn(savedUsersMock);
        BDDMockito.given(usersEntityToUsersMapper.convert(savedUsersMock)).willReturn(userDTO);
        BDDMockito.given(usersDTOMock.getUserID()).willReturn(110L);
        BDDMockito.given(usersDTOMock.getLastName()).willReturn("Popescu");
        BDDMockito.given(usersDTOMock.getFirstName()).willReturn("Marin");
        BDDMockito.given(usersDTOMock.getPassword()).willReturn("sdga32553");
        BDDMockito.given(usersDTOMock.getPhone()).willReturn("07243131");
        BDDMockito.given(usersDTOMock.getAddress()).willReturn("Bd.Severin 156");

        //WHEN
        UsersDTO usersDTO1 = sutUsers.createRegister(usersDTOMock);

        //THEN
        BDDMockito.verify(repositoryUsers).save(usersEntityMock);
        assertThat(usersDTO1).isSameAs(userDTO);
    }

    @Test
    public void given_product_when_add_new_order_then_ordersDTO_returned() {
        //GIVEN
        ProductsDTO productsDTO = ProductsDTO.builder()
                .productID(1000L)
                .name("Calculator of bamboo")
                .category("Office supplies")
                .quantity(20)
                .description("A calculator made of bamboo wood")
                .price(BigDecimal.valueOf(12.50))
                .shippingDays(2)
                .rating(BigDecimal.valueOf(0.00))
                .totalrating(0l)
                .build();

        ArrayList<ProductsDTO> list = new ArrayList<>();
        list.add(productsDTO);

        String searchString = "bamboo";
        var productsEntityMock = Mockito.mock(ProductsEntity.class);
        var productsDTOMock = Mockito.mock(ProductsDTO.class);

        BDDMockito.given(repositoryProducts.findByNameContains(searchString)).willReturn(Arrays.asList(productsEntityMock));
        BDDMockito.given(productsEntityToProductsMapper.convert(productsEntityMock)).willReturn(productsDTO);
        BDDMockito.given(productsDTOMock.getProductID()).willReturn(1000l);
        BDDMockito.given(productsDTOMock.getName()).willReturn("Calculator of bamboo");
        BDDMockito.given(productsDTOMock.getCategory()).willReturn("Office supplies");
        BDDMockito.given(productsDTOMock.getDescription()).willReturn("A calculator made of bamboo wood");
        BDDMockito.given(productsDTOMock.getPrice()).willReturn(BigDecimal.valueOf(12.50));
        BDDMockito.given(productsDTOMock.getRating()).willReturn(BigDecimal.valueOf(0.00));
        BDDMockito.given(productsDTOMock.getTotalrating()).willReturn(0l);
        BDDMockito.given(productsDTOMock.getQuantity()).willReturn(20);
        BDDMockito.given(productsDTOMock.getShippingDays()).willReturn(2);

        //WHEN
        List<ProductsDTO> result = sutProducts.searchBy(searchString);

        //THEN
        assertThat(result.get(0)).isSameAs(list.get(0));
    }
}
