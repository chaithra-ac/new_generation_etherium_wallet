package com.anonymous.ethereumcoldwallet.controller;


import com.anonymous.ethereumcoldwallet.dto.EthereumColdWalletRawTxnDto;
import com.anonymous.ethereumcoldwallet.service.EthereumColdWalletRawTxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author Chaithtra AC
 * @since November 06, 2020
 */

@RestController
@RequestMapping("api/v0")
public class EthereumColdWalletRawTxnController {

    private EthereumColdWalletRawTxnService ethereumColdWalletRawTxnService;

    @Autowired
    public EthereumColdWalletRawTxnController(EthereumColdWalletRawTxnService ethereumColdWalletRawTxnService) {
        this.ethereumColdWalletRawTxnService = ethereumColdWalletRawTxnService;
    }

    @PostMapping("generateTransaction")
    public ResponseEntity<?> generateTransaction(@RequestBody EthereumColdWalletRawTxnDto ethereumColdWalletRawTxnDto) {
        return ethereumColdWalletRawTxnService.generateTransaction(ethereumColdWalletRawTxnDto);
    }
}
