package com.anonymous.ethereumcoldwallet.service.impl;

import com.anonymous.ethereumcoldwallet.dto.EthereumColdWalletDto;
import com.anonymous.ethereumcoldwallet.response.ResponseHandler;
import com.anonymous.ethereumcoldwallet.security.AES;
import com.anonymous.ethereumcoldwallet.service.EthereumColdWalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/***
 * @author Chaithra AC
 * @since November 05, 2020
 */

@Service
public class EthereumColdWalletServiceImpl implements EthereumColdWalletService {

    /**
     * This method helps to generate Ethereum Wallet.
     * At this process only one request will be valid other request will not be consider.
     *
     * @param ethereumColdWalletDto for request payload
     */
    @Override
    public synchronized ResponseEntity<?> generateEthereumColdWallet(EthereumColdWalletDto ethereumColdWalletDto) throws CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String generateNewWalletFile = WalletUtils
                .generateNewWalletFile(ethereumColdWalletDto.getPassphrase(), new File("/home/pawan/"));
        Credentials credentials = WalletUtils.loadCredentials(ethereumColdWalletDto.getPassphrase(), new File("/home/pawan/" + generateNewWalletFile));
        ECKeyPair ecKeyPair = credentials.getEcKeyPair();
        Map<String, String> data = new HashMap<>();
        try {
            data.put("encryptPrivateKey", AES.encryptPrivateKey(ecKeyPair.getPrivateKey().toString(16), ethereumColdWalletDto.getPassphrase()));
            data.put("publicKey", AES.encryptPrivateKey(ecKeyPair.getPublicKey().toString(16), ethereumColdWalletDto.getPassphrase()));
        } catch (Exception e) {
            throw new RuntimeException("Please after some time. Something went wrong!");
        }
        data.put("address", credentials.getAddress());
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "", data, null);
    }

    @Override
    public ResponseEntity<?> accessWalletEthereumColdWalletByEncryptedKey(EthereumColdWalletDto ethereumColdWalletDto) {
        String decryptPrivateKey;
        try {
            decryptPrivateKey = AES.decryptPrivateKey(ethereumColdWalletDto.getEncryptedPrivateKey(), ethereumColdWalletDto.getPassphrase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid encrypted private key! Please provide valid encrypted private key.");
        }
        System.out.println(decryptPrivateKey);
        if (!WalletUtils.isValidPrivateKey(decryptPrivateKey))
            throw new RuntimeException("Invalid private key. Something went wrong.");
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Success", Collections.singletonMap("address",
                Credentials.create(decryptPrivateKey).getAddress()), null);
    }

    @Override
    public ResponseEntity<?> accessWalletEthereumColdWallet(EthereumColdWalletDto ethereumColdWalletDto) {
        if (!WalletUtils.isValidPrivateKey(ethereumColdWalletDto.getPrivateKey()))
            throw new RuntimeException("Invalid private key. Please enter valid private key!");
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Success", Collections.singletonMap("address",
                Credentials.create(ethereumColdWalletDto.getPrivateKey()).getAddress()), null);
    }
}
