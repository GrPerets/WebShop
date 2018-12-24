/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.payment;

import java.util.Set;

/**
 *
 * @author grperets
 */
public interface PaymentTypeService {
    Set<PaymentType> findAll();
    PaymentType findById (Long id);
    PaymentType findByPaymentType (String paymentType);
    PaymentType save (PaymentType paymentType);
    void delete (PaymentType paymentType);
}
