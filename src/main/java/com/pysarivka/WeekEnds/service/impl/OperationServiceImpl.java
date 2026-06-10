package com.pysarivka.WeekEnds.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pysarivka.WeekEnds.dao.OperationRepository;
import com.pysarivka.WeekEnds.domain.Operation;
import com.pysarivka.WeekEnds.service.OperationService;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {

    private final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

    @Autowired
    private OperationRepository operationRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Operation> findById(Long id) {
        logger.info("Get operation by id: " + id);
        return operationRepository.findById(id);
    }

    @Override
    public Operation saveOperation(Operation operation) {
        logger.info("Create new operation: " + operation);
        return operationRepository.save(operation);
    }

    @Override
    public Operation updateOperation(Operation operation) {
        logger.info("Update operation: " + operation);
        return operationRepository.save(operation);
    }

    @Override
    public void deleteOperation(Operation operation) {
        logger.info("Delete operation: " + operation);
        operationRepository.delete(operation);
    }

    @Override
    public void deleteOperationById(Long id) {
        logger.info("Delete operation by id: " + id);
        operationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Operation> findAllOperations() {
        logger.info("Get all operations");
        return operationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Operation> findOperationsByLocationId(Long locationId) {
        logger.info("Get operations for location id: " + locationId);
        return operationRepository.findByLocationId(locationId);
    }
}
