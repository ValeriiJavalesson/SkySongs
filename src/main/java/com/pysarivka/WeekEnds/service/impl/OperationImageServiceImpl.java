package com.pysarivka.WeekEnds.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pysarivka.WeekEnds.dao.OperationImageRepository;
import com.pysarivka.WeekEnds.domain.OperationImage;
import com.pysarivka.WeekEnds.service.OperationImageService;

@Service
@Transactional
public class OperationImageServiceImpl implements OperationImageService {

    private final Logger logger = LoggerFactory.getLogger(OperationImageServiceImpl.class);

    @Autowired
    private OperationImageRepository operationImageRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<OperationImage> findById(Long id) {
        logger.info("Get operation image by id: " + id);
        return operationImageRepository.findById(id);
    }

    @Override
    public OperationImage saveOperationImage(OperationImage operationImage) {
        logger.info("Save new operation image: " + operationImage);
        return operationImageRepository.save(operationImage);
    }

    @Override
    public OperationImage updateOperationImage(OperationImage operationImage) {
        logger.info("Update operation image: " + operationImage);
        return operationImageRepository.save(operationImage);
    }

    @Override
    public void deleteOperationImage(OperationImage operationImage) {
        logger.info("Delete operation image: " + operationImage);
        operationImageRepository.delete(operationImage);
    }

    @Override
    public void deleteOperationImageById(Long id) {
        logger.info("Delete operation image by id: " + id);
        operationImageRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OperationImage> findAllOperationImages() {
        logger.info("Get all operation images");
        return operationImageRepository.findAll();
    }
}
