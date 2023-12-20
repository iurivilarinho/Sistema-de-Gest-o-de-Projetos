package com.br.project.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.br.project.models.enums.SubPilar;

@Service
public class PilarUtil {

	public Integer calculaPontosSubpilar(List<SubPilar> subPilares) {

		Map<SubPilar, Integer> pontosPorSubPilar = Map.of(SubPilar.Socioambiental, 1, SubPilar.Socioeconomico, 1,
				SubPilar.Notoriedade, 3, SubPilar.Excelencia, 1, SubPilar.Inovacao, 1, SubPilar.Cliente_Interno, 1,
				SubPilar.Cliente_Externo, 1);

		return subPilares.stream().map(pontosPorSubPilar::get).filter(Objects::nonNull).mapToInt(Integer::intValue)
				.sum();
	}

	public Integer calculaPontosMetricaHoras(Integer horas) {
		if (horas >= 1 && horas <= 10)
			return 1;
		if (horas <= 30)
			return 3;
		if (horas <= 100)
			return 5;
		return 7;
	}

	public Integer calculaPontosValor(BigDecimal valor) {
		if (valor.compareTo(BigDecimal.ZERO) > 0) {
			if (valor.compareTo(new BigDecimal("1000")) < 0)
				return 1;
			if (valor.compareTo(new BigDecimal("10000")) < 0)
				return 3;
			if (valor.compareTo(new BigDecimal("100000")) < 0)
				return 5;
			if (valor.compareTo(new BigDecimal("100000")) > 0)
				return 9;
		}
		return 0;

	}

}
