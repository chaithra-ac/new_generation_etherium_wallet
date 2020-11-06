package com.anonymous.ethereumcoldwallet.service;

import com.anonymous.ethereumcoldwallet.dto.EthereumColdWalletDto;
import org.springframework.http.ResponseEntity;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/***
 * @author Chaithtra AC
 * @since November 04, 2020
 */

public interface EthereumColdWalletService {
    ResponseEntity<?> generateEthereumColdWallet(EthereumColdWalletDto ethereumColdWalletDto) throws CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException;

    ResponseEntity<?> accessWalletEthereumColdWalletByEncryptedKey(EthereumColdWalletDto ethereumColdWalletDto);

    ResponseEntity<?> accessWalletEthereumColdWallet(EthereumColdWalletDto ethereumColdWalletDto);
}
