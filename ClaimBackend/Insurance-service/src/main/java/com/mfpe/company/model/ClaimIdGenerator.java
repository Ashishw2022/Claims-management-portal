package com.mfpe.company.model;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ClaimIdGenerator implements IdentifierGenerator {
	
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
    	ClaimDetails claimDetails = (ClaimDetails) object;
		String policyNum = claimDetails.getPolicy_No().substring(claimDetails.getPolicy_No().length() - 4);
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int year = cal.get(java.util.Calendar.YEAR) % 10000;
		return "CL" + policyNum + String.format("%04d", year);
    }

}
