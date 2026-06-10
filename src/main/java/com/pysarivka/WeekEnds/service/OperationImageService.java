package com.pysarivka.WeekEnds.service;

import java.util.List;
import java.util.Optional;

import com.pysarivka.WeekEnds.domain.OperationImage;

public interface OperationImageService {
	Optional<OperationImage> findById(Long id);

	OperationImage saveOperationImage(OperationImage operationImage);

	OperationImage updateOperationImage(OperationImage operationImage);

	void deleteOperationImage(OperationImage operationImage);

	void deleteOperationImageById(Long id);

	List<OperationImage> findAllOperationImages();
}
