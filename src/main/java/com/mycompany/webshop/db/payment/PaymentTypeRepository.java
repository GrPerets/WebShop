/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.payment;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author grperets
 */
public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {
    PaymentType findByPaymentType (String paymentType);
    
}
