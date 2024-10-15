package com.playtomic.tests.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playtomic.tests.wallet.model.User;
import com.playtomic.tests.wallet.model.Wallet;
import com.playtomic.tests.wallet.presentation.request.TopupRequest;
import com.playtomic.tests.wallet.service.UsersService;
import com.playtomic.tests.wallet.service.WalletsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void nonExistingWalletReturns404(@Autowired MockMvc mvc) throws Exception {
		mvc.perform(get("/wallets/-1")).andExpect(status().isNotFound());
	}
	@Test
	public void returnsExistingWallet(@Autowired MockMvc mvc) throws Exception {
		User user = usersService.createUser(User.builder().email("a@a.com").password("pass").username("user").build());
		Wallet wallet = walletsService.saveWallet(Wallet.builder().owner(user).balance(BigDecimal.TEN).build());
		String contentAsString = mvc.perform(get("/wallets/" + wallet.getId()))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		assert contentAsString.contains("\"balance\":10.00");
	}

	@Test
	public void walletIsToppedUpCorrectly(@Autowired MockMvc mvc) throws Exception {
		User user = usersService.createUser(User.builder().email("a@a.com").password("pass").username("user").build());
		Wallet wallet = walletsService.saveWallet(Wallet.builder().owner(user).balance(BigDecimal.TEN).build());
		mvc.perform(post("/wallets/"+wallet.getId()+"/topup").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new TopupRequest("4242 4242 4242 4242", BigDecimal.valueOf(15L)))))
				.andExpect(status().isOk());
		String contentAsString = mvc.perform(get("/wallets/" + wallet.getId()))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		assert contentAsString.contains("\"balance\":25.00");
	}

	@Test
	public void topUpFailsAndWalletAmountStaysTheSame(@Autowired MockMvc mvc) throws Exception {
		User user = usersService.createUser(User.builder().email("a@a.com").password("pass").username("user").build());
		Wallet wallet = walletsService.saveWallet(Wallet.builder().owner(user).balance(BigDecimal.TEN).build());
		mvc.perform(post("/wallets/"+wallet.getId()+"/topup").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(new TopupRequest("4242 4242 4242 4242", BigDecimal.valueOf(5L)))))
				.andExpect(status().isBadRequest());
		String contentAsString = mvc.perform(get("/wallets/" + wallet.getId()))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		assert contentAsString.contains("\"balance\":10.00");
	}
}
