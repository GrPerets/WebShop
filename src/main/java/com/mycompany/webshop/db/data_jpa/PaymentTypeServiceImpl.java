/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.data_jpa;

import com.google.common.collect.Sets;
import com.mycompany.webshop.db.payment.PaymentType;
import com.mycompany.webshop.db.payment.PaymentTypeRepository;
import com.mycompany.webshop.db.payment.PaymentTypeService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author grperets
 */
@Service ("paymentTypeService")
@Repository
@Transactional
public class PaymentTypeServiceImpl implements PaymentTypeService {
    private PaymentTypeRepository paymentTypeRepository;

    @Transactional (readOnly=true)
    @Override
    public Set<PaymentType> findAll() {
        return Sets.newHashSet(paymentTypeRepository.findAll());
    }

    @Transactional (readOnly=true)
    @Override
    public PaymentType findById(Long id) {
        return paymentTypeRepository.findOne(id);
    }
    
    @Transactional (readOnly=true)
    @Override
    public PaymentType findByPaymentType(String paymentType) {
        return paymentTypeRepository.findByPaymentType(paymentType);
    }

    @Override
    public PaymentType save(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType);
    }

    @Override
    public void delete(PaymentType paymentType) {
        paymentTypeRepository.delete(paymentType);
    }

    @Autowired
    public void setPaymentTypeRepository(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }
    
}
