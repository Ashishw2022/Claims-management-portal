package com.mfpe.company.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PolicyIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Policy policy = (Policy) object;
        String lastName = policy.getInsured_Last_Name().substring(0, 2).toUpperCase();
        String vehicleNumber = policy.getVehicle_No().substring(policy.getVehicle_No().length() - 3);
        String yearCode = String.format("%02d", LocalDate.now().getYear()% 100);
        return lastName + vehicleNumber + yearCode;
    }

}
