import "./PermutationFlowSection.css";

import {
    MessageSquare,
    Grid2X2,
    Shuffle,
    ArrowLeftRight,
    Lock
} from "lucide-react";

const steps = [

    {
        icon: MessageSquare,
        title: "Mensaje",
        description: "Texto original."
    },

    {
        icon: Grid2X2,
        title: "Bloques",
        description: "Fragmentación."
    },

    {
        icon: Shuffle,
        title: "Permutación",
        description: "Reordenamiento."
    },

    {
        icon: ArrowLeftRight,
        title: "Modular",
        description: "Z₂₅₆."
    },

    {
        icon: Lock,
        title: "Resultado",
        description: "Mensaje cifrado."
    }

];

export default function PermutationFlowSection() {

    return (

        <section className="flow-section">

            <div className="flow-header">

                <span>

                    Flujo del algoritmo

                </span>

                <h2>

                    Transformación del mensaje

                </h2>

                <p>

                    SCMC transforma el mensaje mediante operaciones
                    matemáticas reversibles. Cada etapa posee una función
                    inversa que permite recuperar exactamente el texto
                    original.

                </p>

            </div>

            <div className="flow-container">

                {

                    steps.map((step, index) => {

                        const Icon = step.icon;

                        return (

                            <>

                                <article
                                    key={step.title}
                                    className="flow-card glass"
                                >

                                    <div className="flow-icon">

                                        <Icon size={20}/>

                                    </div>

                                    <h3>

                                        {step.title}

                                    </h3>

                                    <p>

                                        {step.description}

                                    </p>

                                </article>

                                {

                                    index < steps.length - 1 && (

                                        <div className="flow-arrow">

                                            <ArrowLeftRight size={22}/>

                                        </div>

                                    )

                                }

                            </>

                        );

                    })

                }

            </div>

        </section>

    );

}