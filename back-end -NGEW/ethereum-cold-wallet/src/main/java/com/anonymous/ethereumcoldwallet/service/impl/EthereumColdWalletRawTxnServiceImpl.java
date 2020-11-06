package com.anonymous.ethereumcoldwallet.service.impl;

import com.anonymous.ethereumcoldwallet.dto.EthereumColdWalletRawTxnDto;
import com.anonymous.ethereumcoldwallet.response.ResponseHandler;
import com.anonymous.ethereumcoldwallet.service.EthereumColdWalletRawTxnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Collections;

/***
 * @author Chaithtra AC
 * @since November 06, 2020
 */


@Service
public class EthereumColdWalletRawTxnServiceImpl implements EthereumColdWalletRawTxnService {

    @Override
    public ResponseEntity<?> generateTransaction(EthereumColdWalletRawTxnDto ethereumColdWalletRawTxnDto) {
        String privateKey;

        privateKey = ethereumColdWalletRawTxnDto.getPrivateKey();
        isValidPrivateKey(privateKey);

        // Validate to address
        if (!WalletUtils.isValidAddress(ethereumColdWalletRawTxnDto.getToAddress()))
            throw new RuntimeException("Invalid receiver address.");

        RawTransaction rawTransaction = getRawTransaction(ethereumColdWalletRawTxnDto);
        Credentials credentials = Credentials.create(privateKey);
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String signedTransactionHash = Numeric.toHexString(signedMessage);
        return ResponseHandler.generateResponse(
                HttpStatus.OK,
                true,
                "Signed transaction hash successfully generated",
                Collections.singletonMap("signedTransactionHash", signedTransactionHash),
                null);
    }

    private RawTransaction getRawTransaction(EthereumColdWalletRawTxnDto ethereumColdWalletRawTxnDto) {

        final BigInteger GAS_PRICE = Convert.toWei(ethereumColdWalletRawTxnDto.getGasPrice(),
                Convert.Unit.GWEI).toBigInteger();
        final BigInteger GAS_LIMIT = ethereumColdWalletRawTxnDto.getGasLimit().toBigInteger();
        final BigInteger NONCE = BigInteger.valueOf(ethereumColdWalletRawTxnDto.getNonce());
        return getEthereumRawTransaction(ethereumColdWalletRawTxnDto, GAS_PRICE, GAS_LIMIT, NONCE);
    }

    private RawTransaction getEthereumRawTransaction(EthereumColdWalletRawTxnDto ethereumColdWalletRawTxnDto,
                                                     BigInteger GAS_PRICE, BigInteger GAS_LIMIT, BigInteger NONCE) {
        BigInteger value = Convert.toWei(ethereumColdWalletRawTxnDto.getAmount(),
                Convert.Unit.ETHER).toBigInteger();
        return RawTransaction.createEtherTransaction(
                NONCE,
                GAS_PRICE,
                GAS_LIMIT,
                ethereumColdWalletRawTxnDto.getToAddress(),
                value);
    }

    private void isValidPrivateKey(String privateKey) {
        if (!WalletUtils.isValidPrivateKey(privateKey))
            throw new RuntimeException("Invalid private key!. Please enter valid private key.");
    }

}
