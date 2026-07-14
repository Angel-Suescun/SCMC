import "./EncryptServiceSection.css";

import CodeBlock from "../../../../shared/components/CodeBlock";
import Timeline from "../../../../shared/components/Timeline/Timeline";

const encryptServiceCode = `public EncryptResponse encrypt(

    EncryptRequest request

) {

    validate(request);

    Integer shift = normalizeShift(
        request.shift()
    );

    String padded =
        paddingEncryptService.encrypt(
            request.message(),
            request.blockSize()
        );

    String permuted =
        permutationEncryptService.encrypt(
            padded,
            request.permutation(),
            request.blockSize()
        );

    String encrypted =
        modularEncryptService.encrypt(
            permuted,
            shift
        );

    return response(
        padded,
        permuted,
        encrypted
    );

}`;

const timelineItems = [

    {
        title: "Validación",
        description:
            "Comprueba que los parámetros de entrada sean correctos."
    },

    {
        title: "Normalización",
        description:
            "Reduce el shift utilizando módulo 256."
    },

    {
        title: "Padding",
        description:
            "Completa el mensaje hasta obtener bloques de tamaño uniforme."
    },

    {
        title: "Permutación",
        description:
            "Reordena las posiciones dentro de cada bloque."
    },

    {
        title: "Cifrado Modular",
        description:
            "Desplaza los valores ASCII usando aritmética modular."
    }

];

export default function EncryptServiceSection() {

    return (

        <section className="implementation-section">

            <div className="implementation-header">

                <span>

                    Integración

                </span>

                <h2>

                    Método Principal del Algoritmo

                </h2>

                <p>

                    Este método coordina todas las etapas del algoritmo
                    SCMC. Cada fase utiliza el resultado de la anterior,
                    formando una cadena de transformaciones que produce
                    el mensaje cifrado final.

                </p>

            </div>

            <div className="implementation-grid">

                <CodeBlock
                    language="java"
                    title="EncryptService.java"
                    code={encryptServiceCode}
                />

                <div className="implementation-content">

                    <article className="glass explanation-card">

                        <h3>

                            ¿Qué hace este método?

                        </h3>

                        <ul>

                            <li>

                                ✓ Valida los parámetros recibidos.

                            </li>

                            <li>

                                ✓ Normaliza el valor del shift.

                            </li>

                            <li>

                                ✓ Aplica el proceso de padding.

                            </li>

                            <li>

                                ✓ Ejecuta la permutación de bloques.

                            </li>

                            <li>

                                ✓ Realiza el cifrado modular.

                            </li>

                            <li>

                                ✓ Genera la respuesta final.

                            </li>

                        </ul>

                    </article>

                    <article className="glass timeline-card">

                        <h3>

                            Flujo de ejecución

                        </h3>

                        <Timeline
                            items={timelineItems}
                        />

                    </article>

                    <article className="glass math-card">

                        <h3>

                            Composición del algoritmo

                        </h3>

                        <pre>

                            {`SCMC(m) = M(P(B(m)))`}

                        </pre>

                        <p>

                            Donde B representa el Padding,
                            P la Permutación y
                            M el Cifrado Modular.

                        </p>

                    </article>

                </div>

            </div>

        </section>

    );

}