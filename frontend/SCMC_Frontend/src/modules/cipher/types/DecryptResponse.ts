import type { AuditStep } from "./AuditStep";

export interface DecryptResponse {

    encryptedMessage: string;

    permutedMessage: string;

    paddedMessage: string;

    decryptedMessage: string;

    blockSize: number;

    permutation: number[];

    shift: number;

    audit: AuditStep[];

}