package com.anonymous.ethereumcoldwallet.service;


import com.anonymous.ethereumcoldwallet.dto.EthereumColdWalletRawTxnDto;
import org.springframework.http.ResponseEntity;


/***
 * @author Chaithtra AC
 * @since November 04, 2020
 */

public interface EthereumColdWalletRawTxnService {
    ResponseEntity<?> generateTransaction(EthereumColdWalletRawTxnDto ethereumColdWalletRawTxnDto);
}
