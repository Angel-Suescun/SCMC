export interface EncryptRequest {

    message: string;

    blockSize: number;

    permutation: number[];

    shift: number;

}