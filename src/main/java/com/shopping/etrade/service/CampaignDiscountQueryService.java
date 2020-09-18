package com.shopping.etrade.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.etrade.dto.base.MoneyDTO;
import com.shopping.etrade.exception.IncompatibleCurrencyException;
import com.shopping.etrade.exception.ObjectNotFoundException;
import com.shopping.etrade.model.Campaign;
import com.shopping.etrade.model.CampaignDiscount;
import com.shopping.etrade.model.Card;
import com.shopping.etrade.model.CardProduct;
import com.shopping.etrade.model.base.Money;
import com.shopping.etrade.model.repository.CampaignDiscountRepository;
import com.shopping.etrade.model.repository.CardProductRepository;
import com.shopping.etrade.model.repository.CardRepository;
import com.shopping.etrade.model.repository.ProductRepository;
import com.shopping.etrade.service.strategy.campaign.DiscountStrategyContext;

@Service
@Transactional(readOnly = true)
public class CampaignDiscountQueryService {
	private static final int MORE_THAN_THREE_PRODUCT = -1;
	private static final int MAX_CAMPAING_QUANTITY = 3;

	public final CampaignDiscountRepository campaignDiscountRepository;
	public final CardRepository cardRepository;
	public final CardProductRepository cardProductRepository;
	public final ProductRepository productRepository;
	public final DiscountStrategyContext discountStrategyContext;

	@Autowired
	public CampaignDiscountQueryService(CampaignDiscountRepository campaignDiscountRepository,
			CardRepository cardRepository, CardProductRepository cardProductRepository,
			ProductRepository productRepository, DiscountStrategyContext discountStrategyContext) {
		this.campaignDiscountRepository = campaignDiscountRepository;
		this.cardRepository = cardRepository;
		this.cardProductRepository = cardProductRepository;
		this.productRepository = productRepository;
		this.discountStrategyContext = discountStrategyContext;
	}

	public MoneyDTO calculateDiscountedAmountOfCard(Long cardId)
			throws IncompatibleCurrencyException, ObjectNotFoundException {
		MoneyDTO totalDiscountedAmount = new MoneyDTO(BigDecimal.ZERO, MoneyDTO.TL);
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new ObjectNotFoundException());
		List<CardProduct> cardProductList = cardProductRepository.findAllByCard(card);
		for (CardProduct cardProduct : cardProductList) {
			Campaign campaign = cardProduct.getProduct().getCategory().getCampaign();
			if (this.isCampaignActive(campaign.getCampaignStartDate(), campaign.getCampaignEndDate())) {
				totalDiscountedAmount = this.executeDiscountStrategy(totalDiscountedAmount, cardProduct, campaign);
			}
		}
		return totalDiscountedAmount;
	}

	private MoneyDTO executeDiscountStrategy(MoneyDTO totalDiscountedAmount, CardProduct cardProduct, Campaign campaign)
			throws IncompatibleCurrencyException {
		int quantity = cardProduct.getQuantity() > MAX_CAMPAING_QUANTITY ? MORE_THAN_THREE_PRODUCT : cardProduct.getQuantity();
		CampaignDiscount campaignDiscount = campaignDiscountRepository.findByProductCountAndCampaign(quantity,
				campaign);
		discountStrategyContext.setDiscountStrategy(
				discountStrategyContext.calculateDiscountStrategy(campaignDiscount.getDiscountType()));
		MoneyDTO discountedAmount = discountStrategyContext.executeDiscountStrategy(
				Money.toMoneyDTO(cardProduct.getProduct().getPrice()), CampaignDiscount.toDTO(campaignDiscount),
				cardProduct.getQuantity());
		totalDiscountedAmount = totalDiscountedAmount.addMoney(discountedAmount);
		return totalDiscountedAmount;
	}

	private boolean isCampaignActive(Date campaignStartDate, Date campaignEndDate) {
		Date today = new Date();
		return today.after(campaignStartDate) && today.before(campaignEndDate);
	}
}
