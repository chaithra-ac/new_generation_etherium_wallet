package com.anonymous.ethereumcoldwallet.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * @author Chaithtra AC
 * @since November 04, 2020
 */

@NoArgsConstructor
@Getter
@Setter
public class EthereumColdWalletDto {
    private String passphrase;
    private String encryptedPrivateKey;
    private String privateKey;
}
