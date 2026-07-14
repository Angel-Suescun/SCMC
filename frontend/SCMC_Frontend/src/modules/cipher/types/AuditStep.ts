import type { AuditType } from "./AuditType";

export interface AuditStep {

    stepNumber: number;

    title: AuditType;

    description: string;

    input: string;

    output: string;

}