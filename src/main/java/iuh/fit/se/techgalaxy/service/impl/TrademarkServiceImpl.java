package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.response.TrademarkResponse;
import iuh.fit.se.techgalaxy.entities.Trademark;
import iuh.fit.se.techgalaxy.exception.AppException;
import iuh.fit.se.techgalaxy.exception.ErrorCode;
import iuh.fit.se.techgalaxy.mapper.TrademarkMapper;
import iuh.fit.se.techgalaxy.repository.TrademarkRepository;
import iuh.fit.se.techgalaxy.service.TrademarkService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TrademarkServiceImpl implements TrademarkService {
	TrademarkRepository trademarkRepository;
	TrademarkMapper trademarkMapper;

	@Override
	public TrademarkResponse createTrademark(String name) {
		Trademark trademark = new Trademark();
		trademark.setName(name);
		return trademarkMapper.toTrademarkResponse(trademarkRepository.save(trademark));
	}

	@Override
	public boolean deleteTrademark(String id) {

		Trademark trademark = trademarkRepository.findById(id)
				.orElseThrow(() -> new AppException(ErrorCode.TRADEMARK_NOTFOUND));
		if (trademark != null) {
			trademarkRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public TrademarkResponse updateTrademark(String id, String newName) {
		Trademark trademark = trademarkRepository.findById(id)
				.orElseThrow(() -> new AppException(ErrorCode.TRADEMARK_NOTFOUND));
		if (trademark != null) {
			trademark.setName(newName);
			return trademarkMapper.toTrademarkResponse(trademarkRepository.save(trademark));
		}
		return null;

	}

	@Override
	public TrademarkResponse getTrademarkByName(String name) {
		Trademark trademark = trademarkRepository.findTrademarkByName(name);
		return trademarkMapper.toTrademarkResponse(trademark);

	}

	@Override
	public List<TrademarkResponse> getAllTrademarks() {
		List<Trademark> trademarks = trademarkRepository.findAll();
		return trademarks.stream().map(trademarkMapper::toTrademarkResponse).collect(Collectors.toList());
	}

	@Override
	public TrademarkResponse getByID(String id) {
		// TODO Auto-generated method stub
		Trademark trademark = trademarkRepository.findById(id)
				.orElseThrow(() -> new AppException(ErrorCode.TRADEMARK_NOTFOUND));
		return trademarkMapper.toTrademarkResponse(trademark);
	}

}
