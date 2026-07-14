import "./AuditTimeline.css";

import AuditStepComponent from "../AuditStep/AuditStep";

import type { AuditStep } from "../../types/AuditStep";

interface Props{

    audit: AuditStep[];

}

export default function AuditTimeline({

    audit

}:Props){

    if(audit.length===0){

        return null;

    }

    return(

        <section className="audit-timeline">

            <div className="timeline-line"/>

            {

                audit.map(step=>(

                    <AuditStepComponent

                        key={step.stepNumber}

                        step={step}

                    />

                ))

            }

        </section>

    );

}