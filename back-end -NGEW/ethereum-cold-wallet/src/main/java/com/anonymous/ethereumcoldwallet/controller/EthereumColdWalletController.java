package com.anonymous.ethereumcoldwallet.controller;

import com.anonymous.ethereumcoldwallet.dto.EthereumColdWalletDto;
import com.anonymous.ethereumcoldwallet.service.EthereumColdWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/***
 * @author Chaithtra AC
 * @since November 05, 2020
 */

@RestController
@RequestMapping("api/v0")
public class EthereumColdWalletController {

    private EthereumColdWalletService ethereumColdWalletService;

    @Autowired
    public EthereumColdWalletController(EthereumColdWalletService ethereumColdWalletService) {
        this.ethereumColdWalletService = ethereumColdWalletService;
    }

    @PostMapping("generateWallet")
    public ResponseEntity<?> generateEthereumColdWallet(@RequestBody EthereumColdWalletDto ethereumColdWalletDto) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException, IOException {
        return ethereumColdWalletService.generateEthereumColdWallet(ethereumColdWalletDto);
    }

    @PostMapping("accessWalletByEncryptedKey")
    public ResponseEntity<?> accessWalletEthereumColdWalletByEncryptedKey(@RequestBody EthereumColdWalletDto ethereumColdWalletDto) {
        return ethereumColdWalletService.accessWalletEthereumColdWalletByEncryptedKey(ethereumColdWalletDto);
    }

    @PostMapping("accessWallet")
    public ResponseEntity<?> accessWalletEthereumColdWallet(@RequestBody EthereumColdWalletDto ethereumColdWalletDto) {
        return ethereumColdWalletService.accessWalletEthereumColdWallet(ethereumColdWalletDto);
    }

}
