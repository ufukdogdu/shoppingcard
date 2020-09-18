package com.shopping.etrade.service;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.exception.ObjectNotFoundException;
import com.shopping.etrade.mock.CampaignDiscountMocker;
import com.shopping.etrade.mock.CardMocker;
import com.shopping.etrade.mock.CardProductMocker;
import com.shopping.etrade.mock.MoneyMocker;
import com.shopping.etrade.model.CampaignDiscount;
import com.shopping.etrade.model.Card;
import com.shopping.etrade.model.CardProduct;
import com.shopping.etrade.model.repository.CampaignDiscountRepository;
import com.shopping.etrade.model.repository.CardProductRepository;
import com.shopping.etrade.model.repository.CardRepository;
import com.shopping.etrade.model.repository.ProductRepository;
import com.shopping.etrade.service.strategy.campaign.DiscountStrategyContext;

public class CampaignDiscountQueryServiceTest {

	public CampaignDiscountRepository campaignDiscountRepository;
	public CardRepository cardRepository;
	public CardProductRepository cardProductRepository;
	public ProductRepository productRepository;
	public DiscountStrategyContext discountStrategyContext;
	public CampaignDiscountQueryService campaignDiscountQueryService;

	@Before
	public void setup() {
		campaignDiscountRepository = Mockito.mock(CampaignDiscountRepository.class);
		cardRepository = Mockito.mock(CardRepository.class);
		cardProductRepository = Mockito.mock(CardProductRepository.class);
		productRepository = Mockito.mock(ProductRepository.class);
		discountStrategyContext = Mockito.mock(DiscountStrategyContext.class);
		campaignDiscountQueryService = new CampaignDiscountQueryService(campaignDiscountRepository, cardRepository,
				cardProductRepository, productRepository, discountStrategyContext);
	}
	

	@Test
	public void testCalculateDiscountedAmountOfCard() throws IncompatibleCurrencyException, ObjectNotFoundException {
		Card mockCard = CardMocker.genarateCard();
		Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(mockCard));
		List<CardProduct> mockCardProductList = CardProductMocker.generateCardProductList();
		Mockito.when(cardProductRepository.findAllByCard(Mockito.any())).thenReturn(mockCardProductList);
		CampaignDiscount mockCampaignDiscount = CampaignDiscountMocker.generateCampaignDiscount();
		Mockito.when(campaignDiscountRepository.findByProductCountAndCampaign(Mockito.anyInt(), Mockito.any())).thenReturn(mockCampaignDiscount );
		Mockito.when(discountStrategyContext.executeDiscountStrategy(Mockito.any(), Mockito.any(), Mockito.anyInt())).thenReturn(MoneyMocker.generateMoneyDTO());
		MoneyDTO discountedMoney = campaignDiscountQueryService.calculateDiscountedAmountOfCard(1L);
		assertThat(discountedMoney).isNotNull();
	}
	
	@Test
	public void testCalculateDiscountedAmountOfCard2() throws IncompatibleCurrencyException, ObjectNotFoundException {
		Card mockCard = CardMocker.genarateCard();
		Mockito.when(cardRepository.findById(1L)).thenReturn(Optional.of(mockCard));
		List<CardProduct> mockCardProductList = CardProductMocker.generateCardProductList();
		mockCardProductList.get(0).getProduct().getCategory().getCampaign().setCampaignStartDate(new Date(1,1,1));
		mockCardProductList.get(0).getProduct().getCategory().getCampaign().setCampaignEndDate(new Date(2050,1,1));
		Mockito.when(cardProductRepository.findAllByCard(Mockito.any())).thenReturn(mockCardProductList);
		CampaignDiscount mockCampaignDiscount = CampaignDiscountMocker.generateCampaignDiscount();
		Mockito.when(campaignDiscountRepository.findByProductCountAndCampaign(Mockito.anyInt(), Mockito.any())).thenReturn(mockCampaignDiscount );
		Mockito.when(discountStrategyContext.executeDiscountStrategy(Mockito.any(), Mockito.any(), Mockito.anyInt())).thenReturn(MoneyMocker.generateMoneyDTO());
		MoneyDTO discountedMoney = campaignDiscountQueryService.calculateDiscountedAmountOfCard(1L);
		assertThat(discountedMoney).isNotNull();
	}
}
