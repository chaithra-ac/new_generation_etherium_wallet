package com.anonymous.ethereumcoldwallet.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/***
 * @author Chaithtra AC
 * @since November 04, 2020
 */

@NoArgsConstructor
@Getter
@Setter
public class EthereumColdWalletRawTxnDto {

    private String toAddress;
    private BigDecimal amount;
    private BigDecimal gasLimit;
    private BigDecimal gasPrice;
    private int nonce;
    private String privateKey;

}
