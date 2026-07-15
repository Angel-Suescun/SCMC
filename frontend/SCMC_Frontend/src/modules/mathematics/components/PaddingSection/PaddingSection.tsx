import "./PaddingSection.css";

export default function PaddingSection() {

    return (

        <section className="padding-section">

            <div className="padding-header">

                <span>

                    División en bloques

                </span>

                <h2>

                    Padding del mensaje

                </h2>

                <p>

                    El mensaje se divide en bloques de tamaño fijo. Si el último
                    bloque no contiene suficientes caracteres, SCMC completa los
                    espacios restantes utilizando el carácter <strong>~</strong>,
                    permitiendo que todos los bloques tengan la misma longitud.

                </p>

            </div>

            <div className="padding-demo glass">

                <div className="padding-block">

                    <span className="block-title">

                        Bloque 1

                    </span>

                    <div className="cells">

                        <div>H</div>
                        <div>O</div>
                        <div>L</div>
                        <div>A</div>

                    </div>

                </div>

                <div className="padding-block">

                    <span className="block-title">

                        Bloque 2

                    </span>

                    <div className="cells">

                        <div>M</div>
                        <div>U</div>
                        <div>N</div>
                        <div>D</div>

                    </div>

                </div>

                <div className="padding-block">

                    <span className="block-title">

                        Bloque 3

                    </span>

                    <div className="cells">

                        <div>O</div>
                        <div className="padding-cell">~</div>
                        <div className="padding-cell">~</div>
                        <div className="padding-cell">~</div>

                    </div>

                </div>

            </div>

        </section>

    );

}