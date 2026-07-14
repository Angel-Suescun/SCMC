export interface DecryptRequest {

    encryptedMessage: string;

    blockSize: number;

    permutation: number[];

    shift: number;

}