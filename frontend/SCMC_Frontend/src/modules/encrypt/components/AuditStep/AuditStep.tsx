import "./AuditStep.css";

import type { AuditStep } from "../../types/AuditStep";
import { AuditType } from "../../types/AuditType";

import PermutationMatrix from "../PermutationMatrix/PermutationMatrix";

interface Props{

    step: AuditStep;

    permutation?: number[];

}

export default function AuditStepComponent({

    step,

    permutation

}:Props){

    return(

        <article className="audit-step glass">

            <div className="audit-step-header">

                <div className="step-number">

                    {step.stepNumber}

                </div>

                <div>

                    <h3>{step.title}</h3>

                    <p>{step.description}</p>

                </div>

            </div>

            {

                step.title===AuditType.PERMUTACION && permutation && (

                    <PermutationMatrix

                        permutation={permutation}

                    />

                )

            }

            <div className="audit-content">

                <div>

                    <span>Entrada</span>

                    <pre>{step.input}</pre>

                </div>

                <div>

                    <span>Salida</span>

                    <pre>{step.output}</pre>

                </div>

            </div>

        </article>

    );

}