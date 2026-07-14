import "./ComplexitySection.css";

import {

    Clock3,

    HardDrive

} from "lucide-react";

export default function ComplexitySection() {

    return (

        <section className="complexity-section">

            <div className="complexity-header">

                <span>

                    Análisis

                </span>

                <h2>

                    Complejidad computacional

                </h2>

                <p>

                    El algoritmo SCMC procesa cada carácter exactamente una vez,
                    aplicando operaciones constantes sobre cada bloque. Esto hace
                    que su rendimiento sea lineal respecto al tamaño del mensaje.

                </p>

            </div>

            <div className="complexity-grid">

                <article className="complexity-card glass">

                    <div className="complexity-icon">

                        <Clock3 size={30} />

                    </div>

                    <h3>

                        Complejidad temporal

                    </h3>

                    <div className="complexity-value">

                        O(n)

                    </div>

                    <p>

                        Cada carácter es procesado una única vez mediante
                        operaciones de permutación y desplazamiento modular,
                        por lo que el tiempo crece linealmente con la longitud
                        del mensaje.

                    </p>

                </article>

                <article className="complexity-card glass">

                    <div className="complexity-icon">

                        <HardDrive size={30} />

                    </div>

                    <h3>

                        Complejidad espacial

                    </h3>

                    <div className="complexity-value">

                        O(n)

                    </div>

                    <p>

                        El algoritmo únicamente necesita almacenar el mensaje,
                        los bloques generados y la permutación utilizada,
                        requiriendo memoria proporcional al tamaño del texto.

                    </p>

                </article>

            </div>

            <div className="complexity-note glass">

                <h4>

                    ¿Por qué es lineal?

                </h4>

                <p>

                    SCMC no realiza búsquedas exhaustivas, ciclos anidados sobre
                    los caracteres ni cálculos exponenciales. Cada bloque se
                    procesa de manera independiente aplicando una permutación y
                    un desplazamiento modular, permitiendo mantener un costo
                    computacional eficiente incluso para mensajes extensos.

                </p>

            </div>

        </section>

    );

}