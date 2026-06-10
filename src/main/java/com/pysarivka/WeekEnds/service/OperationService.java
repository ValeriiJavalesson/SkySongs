package com.pysarivka.WeekEnds.service;

import java.util.List;
import java.util.Optional;

import com.pysarivka.WeekEnds.domain.Operation;

public interface OperationService {
	Optional<Operation> findById(Long id);

    Operation saveOperation(Operation operation);

    Operation updateOperation(Operation operation);

    void deleteOperation(Operation operation);

    void deleteOperationById(Long id);

    List<Operation> findAllOperations();

    List<Operation> findOperationsByLocationId(Long locationId);
}
