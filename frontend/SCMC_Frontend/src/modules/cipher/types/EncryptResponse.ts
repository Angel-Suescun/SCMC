import type { AuditStep } from "./AuditStep";

export interface EncryptResponse{

    originalMessage:string;

    paddedMessage:string;

    permutedMessage:string;

    encryptedMessage:string;

    blockSize:number;

    permutation:number[];

    shift:number;

    audit:AuditStep[];

}