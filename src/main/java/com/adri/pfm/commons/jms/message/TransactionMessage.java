package com.adri.pfm.commons.jms.message;

import java.math.BigDecimal;
import java.util.Date;

public record TransactionMessage(long transactionId, String concept, Date date, BigDecimal amount, long accountId, String status) {
}
