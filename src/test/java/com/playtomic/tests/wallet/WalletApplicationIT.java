package com.playtomic.tests.wallet;

import com.playtomic.tests.wallet.model.User;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.service.UsersService;
import com.playtomic.tests.wallet.service.WalletsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class WalletApplicationIT {

	@Autowired
	WalletsService walletsService;
	@Autowired
	UsersService usersService;

	@Test
	public void nonExistingWalletReturns404(@Autowired MockMvc mvc) throws Exception {
		mvc.perform(get("/wallets/-1")).andExpect(status().isNotFound());
	}
	@Test
	public void returnsExistingWallet(@Autowired MockMvc mvc) throws Exception {
		User user = usersService.createUser(User.builder().email("a@a.com").password("pass").username("user").build());
		Wallet wallet = walletsService.createWallet(Wallet.builder().owner(user).balance(BigDecimal.TEN).build());
		String contentAsString = mvc.perform(get("/wallets/" + wallet.getId()))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		assert contentAsString.contains("\"balance\":10.00");
	}
}
