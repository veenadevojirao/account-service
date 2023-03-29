package com.maveric.accountservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.BalanceDto;
import com.maveric.accountservice.dto.UserDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.enums.Currency;
import com.maveric.accountservice.enums.Type;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public
class AccountServiceApplicationTests {
	public static final String apiV1 = "/api/v1/customers/1234/accounts";
	public static final String invalidApiV1 = "/api/v1/customers/0000/accounts/0000";
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	void testDoSomething() {  // Noncompliant
		assertTrue(true);

	}


	public static Account getAccount ()
	{
		return Account.builder()
				.customerId("1234")
				.type(Type.SAVINGS)
				.build();
	}
	public static AccountDto getAccountDto ()
	{
		return AccountDto.builder()
				.customerId("1234")
				._id("1234")
				.type(Type.CURRENT)
				.balance(BalanceDto.builder().build())
				.build();


	}
	public static ResponseEntity<BalanceDto> getBalanceDto()
	{
		BalanceDto balanceDto= new BalanceDto();
		balanceDto.setAccountId("1234");
		balanceDto.set_id("123");
		balanceDto.setAmount(1000);
		balanceDto.setCurrency(Currency.DOLLAR);
		return ResponseEntity.status(HttpStatus.OK).body(balanceDto);
	}


	public static ResponseEntity<UserDto> getUserDto()
	{
		UserDto userDto=new UserDto();
		userDto.setId("1234");
		userDto.setEmail("maveric@gmail.com");
		return ResponseEntity.status(HttpStatus.OK).body(userDto);


	}


}